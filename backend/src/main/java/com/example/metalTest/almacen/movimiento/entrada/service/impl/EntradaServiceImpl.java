package com.example.metalTest.almacen.movimiento.entrada.service.impl;

import com.example.metalTest.almacen.repuesto.domain.Stock;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.movimiento.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.movimiento.entrada.controller.response.EntradaResponse;
import com.example.metalTest.almacen.movimiento.entrada.domain.Entrada;
import com.example.metalTest.almacen.movimiento.entrada.mapper.EntradaMapper;
import com.example.metalTest.almacen.movimiento.entrada.repository.EntradaRepository;
import com.example.metalTest.almacen.movimiento.entrada.service.EntradaService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.common.validator.RepositoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaServiceImpl implements EntradaService {
    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    EntradaMapper entradaMapper;

    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<EntradaResponse> getAll() {
        return entradaMapper.toEntradaResponseList(entradaRepository.findAll());
    }

    @Override
    public EntradaResponse create(EntradaRequest entradaRequest) throws ValidateFieldException {
        Entrada entrada = entradaMapper.entradaRequestToEntrada(entradaRequest);
        Repuesto repuesto = (Repuesto) repositoryValidator.getObject(repuestoRepository, entradaRequest.getRepuesto_id());
        Stock stock = repuesto.getStock();
        stock.setActual(repuesto.getStock().getActual() + entradaRequest.getCantidad());
        repuesto.setStock(stock);
        repuesto.setPrecio_unitario(entradaRequest.getPrecio_unitario());
        repuesto.setPrecio_total(repuesto.getPrecio_unitario() * repuesto.getStock().getActual());
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
        Entrada entrada = (Entrada) repositoryValidator.getObject(entradaRepository, id);
        return entradaMapper.toEntradaResponse(entradaRepository.save(entrada));
    }
}
