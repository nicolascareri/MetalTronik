package com.example.metalTest.entrada.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.entrada.controller.request.EntradaRequest;
import com.example.metalTest.entrada.domain.Entrada;
import com.example.metalTest.entrada.mapper.EntradaMapper;
import com.example.metalTest.entrada.repository.EntradaRepository;
import com.example.metalTest.entrada.service.EntradaService;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaServiceImpl implements EntradaService {
    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    EntradaMapper entradaMapper;

    @Override
    public List<Entrada> getAll() {
        return entradaRepository.findAll();
    }

    @Override
    public Entrada create(EntradaRequest entradaRequest) {
        Entrada entrada = entradaMapper.entradaRequestToEntrada(entradaRequest);
        Repuesto repuesto = repuestoRepository.findById(entradaRequest.getRepuesto_cod()).get();
        repuesto.setExistencia(repuesto.getExistencia() + entradaRequest.getCantidad());
        entrada.setRepuesto(repuesto);
        return entradaRepository.save(entrada);
    }

    @Override
    public Entrada getById(Integer id) throws ValidateFieldException {
        Optional<Entrada> optionalEntrada = entradaRepository.findById(id);
        if (!optionalEntrada.isPresent()){
            throw new ValidateFieldException("La entrada que desea acceder no existe","id",String.valueOf(id));
        }
        return entradaRepository.save(optionalEntrada.get());
    }
}
