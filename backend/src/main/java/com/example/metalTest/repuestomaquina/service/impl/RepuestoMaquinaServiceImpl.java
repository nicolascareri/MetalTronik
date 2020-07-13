package com.example.metalTest.repuestomaquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.repuestomaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;
import com.example.metalTest.repuestomaquina.mapper.RepuestoMaquinaMapper;
import com.example.metalTest.repuestomaquina.repository.RepuestoMaquinaRepository;
import com.example.metalTest.repuestomaquina.service.RepuestoMaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoMaquinaServiceImpl implements RepuestoMaquinaService {

    @Autowired
    RepuestoMaquinaRepository repuestoMaquinaRepository;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    RepuestoMaquinaMapper repuestoMaquinaMapper;

    @Override
    public List<RepuestoMaquina> getAll() {
        return repuestoMaquinaRepository.findAll();
    }

    @Override
    public RepuestoMaquina getById(Integer id) throws ValidateFieldException {
        Optional<RepuestoMaquina> opt = repuestoMaquinaRepository.findById(id);
        if (!opt.isPresent()){
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }


    @Override
    public RepuestoMaquina create(RepuestoMaquinaRequest repuestoMaquinaRequest) {
        RepuestoMaquina repuestoMaquina = repuestoMaquinaMapper.repuestoMaquinaRequestToRepuestoMaquina(repuestoMaquinaRequest);
        repuestoMaquina.setMaquina(maquinaRepository.findById(repuestoMaquinaRequest.getMaquina_cod()).get());
        return repuestoMaquinaRepository.save(repuestoMaquina);
    }

    @Override
    public RepuestoMaquina update(RepuestoMaquinaRequest repuestoMaquinaRequest, Integer id) throws ValidateFieldException {
        RepuestoMaquina repuestoMaquina = repuestoMaquinaMapper.repuestoMaquinaRequestToRepuestoMaquina(repuestoMaquinaRequest);
        Optional<RepuestoMaquina> opt = repuestoMaquinaRepository.findById(id);
        if (!opt.isPresent()){
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        repuestoMaquina.setMaquina(maquinaRepository.findById(repuestoMaquinaRequest.getMaquina_cod()).get());
        repuestoMaquina.setId(id);
        return repuestoMaquinaRepository.save(repuestoMaquina);
    }

    @Override
    public RepuestoMaquina getByMaquina(Integer id) throws ValidateFieldException {
        Optional<RepuestoMaquina> opt = repuestoMaquinaRepository.findByMaquina(id);
        if (!opt.isPresent()){
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }
}
