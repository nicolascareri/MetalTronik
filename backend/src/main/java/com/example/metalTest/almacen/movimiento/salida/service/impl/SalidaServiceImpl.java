package com.example.metalTest.almacen.movimiento.salida.service.impl;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.movimiento.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.movimiento.salida.controller.response.SalidaResponse;
import com.example.metalTest.almacen.movimiento.salida.domain.Salida;
import com.example.metalTest.almacen.movimiento.salida.mapper.SalidaMapper;
import com.example.metalTest.almacen.movimiento.salida.repository.SalidaRepository;
import com.example.metalTest.almacen.movimiento.salida.service.SalidaService;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalidaServiceImpl implements SalidaService {
    @Autowired
    SalidaRepository salidaRepository;
    @Autowired
    SalidaMapper salidaMapper;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RepuestoRepository repuestoRepository;
    @Autowired
    TipoRepository tipoRepository;


    @Override
    public List<SalidaResponse> getAll() {
        return salidaMapper.toSalidaResponseList(salidaRepository.findAll());
    }

    @Override
    public SalidaResponse create(SalidaRequest salidaRequest) {
        Salida salida = salidaMapper.salidaRequestToSalida(salidaRequest);
        Repuesto repuesto = repuestoRepository.findById(salidaRequest.getRepuesto_cod()).get();
        repuesto.setExistencia(repuesto.getExistencia() - salidaRequest.getCantidad());
        repuesto.setPrecio_total(repuesto.getPrecio_unitario() * repuesto.getExistencia());
        salida.setRepuesto(repuesto);
        salida.setSector(tipoRepository.findById(salidaRequest.getSector_id()).get());
        salida.setSolicitante(usuarioRepository.findById(salidaRequest.getSolicitante_id()).get());
        return salidaMapper.toSalidaResponse(salidaRepository.save(salida));
    }
}
