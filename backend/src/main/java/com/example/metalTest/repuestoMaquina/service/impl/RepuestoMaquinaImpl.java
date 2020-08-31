package com.example.metalTest.repuestoMaquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
import com.example.metalTest.repuestoMaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquinaPk;
import com.example.metalTest.repuestoMaquina.service.RepuestoMaquinaService;
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

    @Override
    public List<RepuestoMaquina> vincular(List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, Integer id) throws ValidateFieldException {
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
            repuestoMaquina.setCantidad_instalada(repuestoMaquinaRequest.getCantidadInstalada());
            repuestoMaquina.setRepuestoMaquinaPk(repuestoMaquinaPk);
            repuestoMaquinaList.add(repuestoMaquina);
        });
        maquina.setRepuestoMaquinaList(repuestoMaquinaList);
        maquinaRepository.save(maquina);
        return repuestoMaquinaList;
    }
}
