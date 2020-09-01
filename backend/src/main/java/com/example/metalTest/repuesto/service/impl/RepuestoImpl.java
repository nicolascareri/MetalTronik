package com.example.metalTest.repuesto.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.repuesto.controller.response.RepuestoReducidoResponse;
import com.example.metalTest.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
import com.example.metalTest.repuesto.service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoImpl implements RepuestoService {

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    RepuestoMapper repuestoMapper;

    @Override
    public List<RepuestoReducidoResponse> getAll() {
        return repuestoMapper.toRepuestoReducidoResponseList(repuestoRepository.findAll());
    }

    @Override
    public RepuestoResponse getById(Integer id) throws ValidateFieldException {
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        return repuestoMapper.toRepuestoResponse(opt.get());
    }


    @Override
    public Repuesto create(RepuestoRequest repuestoRequest) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
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

    @Override
    public List<Repuesto> getByMaquina(Integer id) throws ValidateFieldException {
        return repuestoRepository.findByMaquina(id);
    }

}
