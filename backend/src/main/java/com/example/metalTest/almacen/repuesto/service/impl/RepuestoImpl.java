package com.example.metalTest.almacen.repuesto.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.repuesto.service.RepuestoService;
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

    @Override
    public List<Repuesto> getAll() {
        List<Repuesto> a = repuestoRepository.findAll();
        return a;
    }

    @Override
    public Repuesto getById(Integer id) throws ValidateFieldException {
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }


    @Override
    public Repuesto create(RepuestoRequest repuestoRequest) {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        repuesto.setTipo_repuesto(tipoRepository.findById(repuestoRequest.getTipoRepuesto_id()).get());
        repuesto.setCantidad_instalada(0);
        repuesto.setPrecio_unitario(0);
        repuesto.setPrecio_total(0);
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        repuesto.setId(id);
        return repuestoRepository.save(repuesto);
    }



}
