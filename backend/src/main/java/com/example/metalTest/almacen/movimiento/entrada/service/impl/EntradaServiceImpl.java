package com.example.metalTest.almacen.movimiento.entrada.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.movimiento.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.movimiento.entrada.controller.response.EntradaResponse;
import com.example.metalTest.almacen.movimiento.entrada.domain.Entrada;
import com.example.metalTest.almacen.movimiento.entrada.mapper.EntradaMapper;
import com.example.metalTest.almacen.movimiento.entrada.repository.EntradaRepository;
import com.example.metalTest.almacen.movimiento.entrada.service.EntradaService;
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
        Repuesto repuesto = repuestoRepository.findById(entradaRequest.getRepuesto_id()).get();
        repuesto.setExistencia(repuesto.getExistencia() + entradaRequest.getCantidad());
        repuesto.setPrecio_total(entradaRequest.getPrecio_total());
        repuesto.setPrecio_unitario(entradaRequest.getPrecio_unitario());

        entrada.setCantidad(entradaRequest.getCantidad());
        entrada.setNumeroOrdenCompra(entradaRequest.getNumeroOrdenCompra());
        entrada.setProveedor(entradaRequest.getProveedor());
        entrada.setFecha(entradaRequest.getFecha());
        entrada.setRepuesto(repuesto);
        entradaRepository.save(entrada);
        return entradaMapper.toEntradaResponse(entrada);
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
