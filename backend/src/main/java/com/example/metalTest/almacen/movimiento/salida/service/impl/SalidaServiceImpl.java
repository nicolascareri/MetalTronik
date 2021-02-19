package com.example.metalTest.almacen.movimiento.salida.service.impl;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.domain.Stock;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.movimiento.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.movimiento.salida.controller.response.SalidaResponse;
import com.example.metalTest.almacen.movimiento.salida.domain.Salida;
import com.example.metalTest.almacen.movimiento.salida.mapper.SalidaMapper;
import com.example.metalTest.almacen.movimiento.salida.repository.SalidaRepository;
import com.example.metalTest.almacen.movimiento.salida.service.SalidaService;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalidaServiceImpl implements SalidaService {
    @Autowired
    SalidaRepository salidaRepository;
    @Autowired
    SalidaMapper salidaMapper;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    RepuestoRepository repuestoRepository;
    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    RepositoryValidator repositoryValidator;


    @Override
    public List<SalidaResponse> getAll() {
        return salidaMapper.toSalidaResponseList(salidaRepository.findAll());
    }

    @Override
    public SalidaResponse create(SalidaRequest salidaRequest) throws ValidateFieldException {
        Salida salida = salidaMapper.salidaRequestToSalida(salidaRequest);
        Repuesto repuesto = (Repuesto) repositoryValidator.getObject(repuestoRepository, salidaRequest.getRepuesto_id());
        Stock stock = repuesto.getStock();
        stock.setActual(repuesto.getStock().getActual() - salidaRequest.getCantidad());
        repuesto.setStock(stock);
        repuesto.setPrecio_total(repuesto.getPrecio_unitario() * repuesto.getStock().getActual());
        salida.setRepuesto(repuesto);
        salida.setSector((Tipo) repositoryValidator.getObject(tipoRepository,salidaRequest.getSector_id()));
        salida.setSolicitante((Personal)repositoryValidator.getObject(personalRepository, salidaRequest.getSolicitante_id()));
        return salidaMapper.toSalidaResponse(salidaRepository.save(salida));
    }
}
