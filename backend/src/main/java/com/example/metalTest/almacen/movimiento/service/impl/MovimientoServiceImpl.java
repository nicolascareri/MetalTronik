package com.example.metalTest.almacen.movimiento.service.impl;

import com.example.metalTest.almacen.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.almacen.movimiento.domain.Movimiento;
import com.example.metalTest.almacen.movimiento.mapper.MovimientoMapper;
import com.example.metalTest.almacen.movimiento.repository.MovimientoRepository;
import com.example.metalTest.almacen.movimiento.service.MovimientoService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    MovimientoMapper movimientoMapper;

    @Override
    public List<Movimiento> getAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento create(MovimientoRequest movimientoRequest) {
        Movimiento movimiento = movimientoMapper.movimientoRequestToMovimiento(movimientoRequest);
        Repuesto repuesto = repuestoRepository.findById(movimientoRequest.getRepuesto_cod()).get();
        movimiento.setRepuesto(repuesto);
        int precio = repuesto.getPrecio();
        int existencia = repuesto.getExistencia();
//        if (movimiento.getTipoMovimiento() == TipoMovimiento.ENTRADA.getValue()){
//            repuesto.setPrecio(precio + movimiento.getPrecio());
//            repuesto.setExistencia(existencia + movimiento.getCantidad());
//            //setear proveedor cuando sea entidad
//        }else if (movimiento.getTipoMovimiento() == TipoMovimiento.SALIDA.getValue()){
//            repuesto.setExistencia(existencia - movimiento.getCantidad());
//            movimiento.setSolicitante(usuarioRepository.findById(movimientoRequest.getSolicitante_cod()).get());
//            movimiento.setSector(sectorRepository.findById(movimientoRequest.getSector_cod()).get());
//        }
//        else {
//            throw new ValidateFieldException("El tipo de movimiento no existe", "tipoMovimiento",String.valueOf(movimiento.getTipoMovimiento()));
//        }
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento update(MovimientoRequest movimientoRequest, Integer id) {
        Movimiento movimiento = movimientoMapper.movimientoRequestToMovimiento(movimientoRequest);
        //setear repuesto
        return movimientoRepository.save(movimiento);
    }


}
