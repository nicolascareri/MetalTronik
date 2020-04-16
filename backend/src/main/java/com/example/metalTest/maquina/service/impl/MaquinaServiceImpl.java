package com.example.metalTest.maquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.maquina.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements MaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    @Override
    public List<Maquina> getAll() {
        return maquinaRepository.findAll();
    }

    @Override
    public Maquina getById(Integer id) throws ValidateFieldException {
        Optional<Maquina> opt = maquinaRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        else {
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", id.toString());
        }

    }

    @Override
    public Maquina save(Maquina maquina) throws ValidateFieldException {

        if (maquinaRepository.checkCodigoExistance(maquina.getMaquina_cod()) != 0) {
            throw new ValidateFieldException("El codigo ya tiene una maquina asociada", "maquina_cod", maquina.getMaquina_cod());
        }
        else{
            return maquinaRepository.save(maquina);
        }

    }

    @Override
    public Maquina getByCod(String cod) throws ValidateFieldException {
        Optional<Maquina> opt = Optional.ofNullable(maquinaRepository.getByCod(cod));
        if(opt.isPresent()){
            return opt.get();
        }
        else {
            throw new ValidateFieldException("La maquina que desea acceder no existe", "maquina_cod", cod);
        }
    }
}
