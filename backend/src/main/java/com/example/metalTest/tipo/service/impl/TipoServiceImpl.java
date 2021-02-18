package com.example.metalTest.tipo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.mapper.TipoMapper;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.tipo.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    TipoMapper tipoMapper;
    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<Tipo> getAll() {
        return tipoRepository.findAll();
    }

    @Override
    public Tipo getById(Integer id) throws ValidateFieldException {
        return (Tipo)repositoryValidator.getObject(tipoRepository, id);
    }

    @Override
    public Tipo create(TipoRequest tipoRequest) throws ValidateFieldException {
        Tipo tipo = tipoMapper.tipoRequestToTipo(tipoRequest);
        tipo.setEstado(Estado.ACTIVO);
        return tipoRepository.save(tipo);
    }

    @Override
    public Tipo update(TipoRequest tipoRequest, Integer id) throws ValidateFieldException {
        Tipo tipo = (Tipo) repositoryValidator.getObject(tipoRepository, id);
        tipo.setTipo(tipoRequest.getTipo());
        tipo.setNombre(tipoRequest.getNombre());
        tipo.setEstado(tipoRequest.getEstado());
        tipo.setId(id);
        return tipoRepository.save(tipo);
    }

    @Override
    public List<Tipo> getByTipo(String tipo) {
        return tipoRepository.getTipo(tipo, Estado.ACTIVO);
    }

    @Override
    public List<String> getTipos() {
        return tipoRepository.getTipos(Estado.ACTIVO);
    }

    @Override
    public Boolean delete(Integer id) throws ValidateFieldException {
        repositoryValidator.getObject(tipoRepository, id);
        try{
            tipoRepository.deleteById(id);
        }catch (Exception e){
            throw new ValidateFieldException(e.getMessage(), "  id", String.valueOf(id));
        }
        return true;

    }


}
