package com.example.metalTest.almacen.ajusteStock.service.impl;

import com.example.metalTest.almacen.ajusteStock.controller.request.AjusteRequest;
import com.example.metalTest.almacen.ajusteStock.controller.response.AjusteResponse;
import com.example.metalTest.almacen.ajusteStock.domain.AjusteStock;
import com.example.metalTest.almacen.ajusteStock.mapper.AjusteMapper;
import com.example.metalTest.almacen.ajusteStock.repository.AjusteRepository;
import com.example.metalTest.almacen.ajusteStock.service.AjusteService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.validator.RepositoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AjusteServiceImpl implements AjusteService {

    @Autowired
    AjusteRepository ajusteRepository;
    @Autowired
    AjusteMapper ajusteMapper;
    @Autowired
    RepuestoRepository repuestoRepository;

    @Override
    public List<AjusteResponse> getAll() {
        List<AjusteStock> correcciones = ajusteRepository.findAll();
        List<AjusteResponse> response = new ArrayList<>();
        for (AjusteStock correccion: correcciones) {
            AjusteResponse a = new AjusteResponse();
            a.setFecha_correccion(correccion.getFecha_correccion());
            a.setRepuesto(correccion.getRepuesto_id());
            a.setStock(correccion.getStock());
            a.setStock_ajustado(correccion.getStock_ajustado());
            response.add(a);
        }
        return response;
    }

    @Override
    public AjusteResponse create(AjusteRequest ajusteRequest, Integer repuesto_id) throws ValidateFieldException {
        AjusteStock ajuste = ajusteMapper.ajusteRequestToAjusteStock(ajusteRequest);
        RepositoryValidator<Repuesto> repuestoRepositoryValidator = new  RepositoryValidator();
        Repuesto repuesto = repuestoRepositoryValidator.getObject(repuestoRepository,repuesto_id);
        repuesto.setPrecio_total(repuesto.getPrecio_unitario() * ajusteRequest.getStock());
        repuesto.setMarca(ajusteRequest.getMarca());
        repuesto.setId(repuesto_id);
        repuestoRepository.save(repuesto);
        ajuste.setRepuesto_id(repuesto);
        ajuste.setStock_ajustado(ajusteRequest.getStock_ajustado());
        ajusteRepository.save(ajuste);
        return ajusteMapper.ajusteStockToAjusteResponse(ajuste);
    }
}
