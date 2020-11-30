package com.example.metalTest.tipos.tipo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.tipos.tipo.domain.Tipo;
import com.example.metalTest.tipos.tipo.repository.TipoRepository;
import com.example.metalTest.tipos.tipo.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepository tipoRepository;

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
    public Tipo create(Tipo tipo) throws ValidateFieldException {
        if (tipo.getEstado() != Estado.ACTIVO.getValue() && tipo.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tipo.getEstado()));
        }
        return tipoRepository.save(tipo);
    }

    @Override
    public Tipo update(Tipo tipo, Integer id) throws ValidateFieldException {
        Optional<Tipo> opt = tipoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El tipo que desea acceder no existe", "id", String.valueOf(id));
        }
        if (tipo.getEstado() != Estado.ACTIVO.getValue() && tipo.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tipo.getEstado()));
        }
        tipo.setId(id);
        return tipoRepository.save(tipo);
    }
}
