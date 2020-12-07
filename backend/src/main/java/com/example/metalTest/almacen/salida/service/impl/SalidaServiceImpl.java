package com.example.metalTest.almacen.salida.service.impl;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.salida.controller.response.SalidaResponse;
import com.example.metalTest.almacen.salida.domain.Salida;
import com.example.metalTest.almacen.salida.mapper.SalidaMapper;
import com.example.metalTest.almacen.salida.repository.SalidaRepository;
import com.example.metalTest.almacen.salida.service.SalidaService;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
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
    SectorRepository sectorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RepuestoRepository repuestoRepository;


    @Override
    public List<SalidaResponse> getAll() {
        return salidaMapper.toSalidaResponseList(salidaRepository.findAll());
    }

    @Override
    public SalidaResponse create(SalidaRequest salidaRequest) {
        Salida salida = salidaMapper.salidaRequestToSalida(salidaRequest);
        Repuesto repuesto = repuestoRepository.findById(salidaRequest.getRepuesto_cod()).get();
        repuesto.setExistencia(repuesto.getExistencia() - salidaRequest.getCantidad());
        salida.setRepuesto(repuesto);
        salida.setSector(sectorRepository.findById(salidaRequest.getSector_cod()).get());
        salida.setSolicitante(usuarioRepository.findById(salidaRequest.getSolicitante_cod()).get());
        return salidaMapper.toSalidaResponse(salidaRepository.save(salida));
    }
}
