package com.example.metalTest.tipo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.mapper.TipoMapper;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.tipo.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    TipoMapper tipoMapper;

    @Override
    public List<Tipo> getAll() {
        return tipoRepository.findAll();
    }

    @Override
    public Tipo getById(Integer id) throws ValidateFieldException {
        Optional<Tipo> opt = tipoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El tipo que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }

    @Override
    public Tipo create(TipoRequest tipoRequest) throws ValidateFieldException {
        Tipo tipo = tipoMapper.tipoRequestToTipo(tipoRequest);
        return tipoRepository.save(tipo);
    }

    @Override
    public Tipo update(TipoRequest tipoRequest, Integer id) throws ValidateFieldException {
        Optional<Tipo> opt = tipoRepository.findById(id);
        Tipo tipo = tipoMapper.tipoRequestToTipo(tipoRequest);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El tipo que desea acceder no existe", "id", String.valueOf(id));
        }
        tipo.setId(id);
        return tipoRepository.save(tipo);
    }

    @Override
    public List<Tipo> getByTipo(String tipo) {
        return tipoRepository.getTipo(tipo);
    }

    @Override
    public List<String> getTipos() {
        return tipoRepository.getTipos();
    }


}
