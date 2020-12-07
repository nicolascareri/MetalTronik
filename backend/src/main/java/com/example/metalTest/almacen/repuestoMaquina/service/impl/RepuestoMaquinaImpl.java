package com.example.metalTest.almacen.repuestoMaquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.repuestoMaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquinaPk;
import com.example.metalTest.almacen.repuestoMaquina.mapper.RepuestoMaquinaMapper;
import com.example.metalTest.almacen.repuestoMaquina.repository.RepuestoMaquinaRepository;
import com.example.metalTest.almacen.repuestoMaquina.service.RepuestoMaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepuestoMaquinaImpl implements RepuestoMaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    RepuestoMapper repuestoMapper;

    @Autowired
    RepuestoMaquinaMapper repuestoMaquinaMapper;

    @Autowired
    RepuestoMaquinaRepository repuestoMaquinaRepository;

    @Override
    public List<RepuestoMaquinaResponse> vincular(List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, Integer id) throws ValidateFieldException {
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(id);
        if (!optionalMaquina.isPresent()){
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", String.valueOf(id));
        }
        Maquina maquina = optionalMaquina.get();
        List<RepuestoMaquina> repuestoMaquinaList = new ArrayList<>();
        repuestoMaquinaRequestList.forEach(repuestoMaquinaRequest -> {
            Repuesto repuesto = repuestoRepository.findById(repuestoMaquinaRequest.getRepuesto_cod()).get();
            RepuestoMaquina repuestoMaquina = new RepuestoMaquina();
            RepuestoMaquinaPk repuestoMaquinaPk = new RepuestoMaquinaPk();
            repuestoMaquinaPk.setMaquina(maquina);
            repuestoMaquinaPk.setRepuesto(repuesto);
            repuestoMaquina.setCantidad_instalada(repuestoMaquinaRequest.getCantidad_instalada());
            repuestoMaquina.setRepuestoMaquinaPk(repuestoMaquinaPk);
            repuestoMaquinaList.add(repuestoMaquina);
        });
        repuestoMaquinaRequestList.forEach(repuestoMaquinaRequest -> {
            Repuesto repuesto = repuestoRepository.findById(repuestoMaquinaRequest.getRepuesto_cod()).get();
            repuesto.setRepuestoMaquinaList(repuestoMaquinaList);
            repuestoRepository.save(repuesto);
        });
        maquina.setRepuestoMaquinaList(repuestoMaquinaList);
        maquinaRepository.save(maquina);
        return repuestoMaquinaMapper.repuestoMaquinaListToRepuestoMaquinaResponseList(repuestoMaquinaList);
    }

    @Override
    public List<RepuestoMaquinaResponse> getAll() {
        return repuestoMaquinaMapper.repuestoMaquinaListToRepuestoMaquinaResponseList(repuestoMaquinaRepository.findAll());
    }
}
