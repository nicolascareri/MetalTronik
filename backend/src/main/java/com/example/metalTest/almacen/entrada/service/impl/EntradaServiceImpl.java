package com.example.metalTest.almacen.entrada.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.entrada.controller.response.EntradaResponse;
import com.example.metalTest.almacen.entrada.domain.Entrada;
import com.example.metalTest.almacen.entrada.mapper.EntradaMapper;
import com.example.metalTest.almacen.entrada.repository.EntradaRepository;
import com.example.metalTest.almacen.entrada.service.EntradaService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
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
    public List<EntradaResponse> getAll() {
        return entradaMapper.toEntradaResponseList(entradaRepository.findAll());
    }

    @Override
    public EntradaResponse create(EntradaRequest entradaRequest) {
        Entrada entrada = entradaMapper.entradaRequestToEntrada(entradaRequest);
        Repuesto repuesto = repuestoRepository.findById(entradaRequest.getRepuesto_cod()).get();
        repuesto.setExistencia(repuesto.getExistencia() + entradaRequest.getCantiad());
        entrada.setRepuesto(repuesto);
        return entradaMapper.toEntradaResponse(entradaRepository.save(entrada));
    }

    @Override
    public EntradaResponse getById(Integer id) throws ValidateFieldException {
        Optional<Entrada> optionalEntrada = entradaRepository.findById(id);
        if (!optionalEntrada.isPresent()){
            throw new ValidateFieldException("La entrada que desea acceder no existe","id",String.valueOf(id));
        }
        return entradaMapper.toEntradaResponse(entradaRepository.save(optionalEntrada.get()));
    }
}
