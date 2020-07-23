package com.example.metalTest.prioridades.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.prioridades.domain.Prioridades;
import com.example.metalTest.prioridades.repository.PrioridadesRepository;
import com.example.metalTest.prioridades.service.PrioridadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioridadesServiceImpl implements PrioridadesService {
    @Autowired
    PrioridadesRepository prioridadesRepository;

    @Override
    public List<Prioridades> getAll() {
        return prioridadesRepository.findAll();
    }

    @Override
    public Prioridades getById(Integer id) throws ValidateFieldException {
        Optional<Prioridades> opt = prioridadesRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La prioridad que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }

    @Override
    public Prioridades create(Prioridades prioridades) throws ValidateFieldException {
        if (prioridades.getEstado() != Estado.ACTIVO.getValue() && prioridades.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(prioridades.getEstado()));
        }
        return prioridadesRepository.save(prioridades);
    }

    @Override
    public Prioridades update(Prioridades prioridades, Integer id) throws ValidateFieldException {
        Optional<Prioridades> opt = prioridadesRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La prioridad que desea acceder no existe", "id", String.valueOf(id));
        }
        if (prioridades.getEstado() != Estado.ACTIVO.getValue() && prioridades.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(prioridades.getEstado()));
        }
        prioridades.setId(id);
        return prioridadesRepository.save(prioridades);
    }
}
