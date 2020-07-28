package com.example.metalTest.repuesto.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.repuesto.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.repuesto.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
import com.example.metalTest.repuesto.service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Repuesto> getAll() {
        return repuestoRepository.findAll();
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
    public Repuesto create(RepuestoRequest repuestoRequest) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(repuestoRequest.getMaquina_cod());
        if (optionalMaquina.isPresent()) {
            repuesto.setMaquina(optionalMaquina.get());
        }
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        repuesto.setMaquina(maquinaRepository.findById(repuestoRequest.getMaquina_cod()).get());
        repuesto.setId(id);
        return repuestoRepository.save(repuesto);
    }

    @Override
    public List<Repuesto> getByMaquina(Integer id) throws ValidateFieldException {
        return repuestoRepository.findByMaquina(id);
    }

    @Override
    public List<RepuestoMaquinaResponse> vincular(List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, Integer id) throws ValidateFieldException {
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(id);
        if (!optionalMaquina.isPresent()){
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", String.valueOf(id));
        }
        Maquina maquina = optionalMaquina.get();
        List<RepuestoMaquinaResponse> repuestoResponseList = new ArrayList<RepuestoMaquinaResponse>();
        repuestoMaquinaRequestList.forEach(repuestoMaquinaRequest -> {
            Repuesto repuesto = repuestoRepository.findById(repuestoMaquinaRequest.getRepuesto_cod()).get();
            repuesto.setMaquina(maquina);
            repuesto.setCantidadInstalada(repuestoMaquinaRequest.getCantidadInstalada());
            RepuestoMaquinaResponse repuestoMaquinaResponse = repuestoMapper.repuestoToRepuestoMaquinaResponse(repuestoRepository.save(repuesto));
            repuestoResponseList.add(repuestoMaquinaResponse);
        });
        return repuestoResponseList;
    }
}
