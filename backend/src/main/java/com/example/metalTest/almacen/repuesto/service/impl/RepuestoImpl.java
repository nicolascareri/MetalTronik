package com.example.metalTest.almacen.repuesto.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.repuesto.service.RepuestoService;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoImpl implements RepuestoService {

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    RepuestoMapper repuestoMapper;
    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<Repuesto> getAll() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto getById(Integer id) throws ValidateFieldException {
        return (Repuesto) repositoryValidator.getObject(repuestoRepository, id);
    }


    @Override
    public Repuesto create(RepuestoRequest repuestoRequest) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        repuesto.setTipo_repuesto((Tipo) repositoryValidator.getObject(tipoRepository, repuestoRequest.getTipoRepuesto_id()));
        repuesto.setStock(repuestoMapper.stockRequestToStock(repuestoRequest.getStock()));
        repuesto.setCantidad_instalada(0);
        repuesto.setPrecio_unitario(0);
        repuesto.setPrecio_total(0);
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException {
        repositoryValidator.getObject(repuestoRepository, id);
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        repuesto.setTipo_repuesto((Tipo) repositoryValidator.getObject(tipoRepository, repuestoRequest.getTipoRepuesto_id()));
        repuesto.setId(id);
        return repuestoRepository.save(repuesto);
    }



}
