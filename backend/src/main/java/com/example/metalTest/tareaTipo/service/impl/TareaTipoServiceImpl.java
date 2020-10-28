package com.example.metalTest.tareaTipo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tareaTipo.controller.request.TareaTipoRequest;
import com.example.metalTest.tareaTipo.domain.TareaTipo;
import com.example.metalTest.tareaTipo.mapper.TareaTipoMapper;
import com.example.metalTest.tareaTipo.repository.TareaTipoRepository;
import com.example.metalTest.tareaTipo.service.TareaTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaTipoServiceImpl implements TareaTipoService {
    @Autowired
    TareaTipoRepository tareaTipoRepository;
    @Autowired
    TareaTipoMapper tareaTipoMapper;

    @Override
    public List<TareaTipo> getAll() { return tareaTipoRepository.findAll(); }

    @Override
    public TareaTipo getById(Integer id) throws ValidateFieldException {
        Optional<TareaTipo> optionalTareaTipo = tareaTipoRepository.findById(id);
        if (!optionalTareaTipo.isPresent()){
            throw new ValidateFieldException("Tarea tipo no encontrada", "", "");
        }
        return optionalTareaTipo.get();
    }

    @Override
    public TareaTipo create(TareaTipoRequest tareaTipoRequest) throws ValidateFieldException {
        TareaTipo tareaTipo = tareaTipoMapper.tareaTipoRequestToTareaTipo(tareaTipoRequest);
        return tareaTipoRepository.save(tareaTipo);
    }

    @Override
    public TareaTipo update(TareaTipoRequest tareaTipoRequest, Integer id) throws ValidateFieldException {
        Optional<TareaTipo> optionalTareaTipo = tareaTipoRepository.findById(id);
        if (!optionalTareaTipo.isPresent()){
            throw new ValidateFieldException("TareaTipo no encontrada", "", "") ;
        }
        TareaTipo tareaTipo = tareaTipoMapper.tareaTipoRequestToTareaTipo(tareaTipoRequest);
        tareaTipo.setId(id);
        return tareaTipoRepository.save(tareaTipo);
    }

}
