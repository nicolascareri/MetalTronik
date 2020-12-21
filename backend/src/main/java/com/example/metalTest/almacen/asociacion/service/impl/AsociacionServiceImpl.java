package com.example.metalTest.almacen.asociacion.service.impl;

import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.controller.request.RepuestoAsociarRequest;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.almacen.asociacion.repository.AsociacionRepository;
import com.example.metalTest.almacen.asociacion.service.AsosiacionService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsociacionServiceImpl implements AsosiacionService {

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    RepuestoMapper repuestoMapper;

    @Autowired
    ParteRepository parteRepository;
    ParteBuscador parteBuscador = new ParteBuscador();
    @Autowired
    AsociacionRepository asociacionRepository;

    @Override
    public void asociar(AsociarList asociarList){
        Maquina maquina = maquinaRepository.findById(asociarList.getMaquina_id()).get();
        Parte parte = parteBuscador.getParte(asociarList.getParte_id(), parteRepository.getAllByMaquina(asociarList.getMaquina_id()));
        for (RepuestoAsociarRequest ra: asociarList.getRequestList()) {
            Integer cantidad_instalada = ra.getCantidad_instalada();
            Integer repuesto_id = ra.getRepuesto_id();
            Repuesto repuesto = repuestoRepository.findById(repuesto_id).get();
            updateRepuesto(repuesto, cantidad_instalada, repuesto_id);
            Asociacion asociacion = setCampos(maquina, parte, repuesto, cantidad_instalada);
            asociacionRepository.save(asociacion);
        }
    }

    private Asociacion setCampos(Maquina maquina, Parte parte, Repuesto repuesto, Integer cantidad_instalada) {
        if (parte != null)
            return new Asociacion(0, maquina.getMaquina_cod(), maquina.getSector().getNombre(), maquina.getPlanta().getNombre(), repuesto.getNombre(), repuesto.getModelo(), cantidad_instalada, parte.getNombre());
        else
            return new Asociacion(0, maquina.getMaquina_cod(), maquina.getSector().getNombre(), maquina.getPlanta().getNombre(), repuesto.getNombre(), repuesto.getModelo(), cantidad_instalada, "-----");
    }


    @Override
    public List<Asociacion> getVinculados() {
        return asociacionRepository.findAll();
    }

    @Override
    public List<Maquina> getSinVincular() {
        return asociacionRepository.getSinAsociar();
    }
    private void updateRepuesto(Repuesto repuesto, Integer cantidad, Integer repuesto_id){
        repuesto.setCantidad_instalada(repuesto.getCantidad_instalada() + cantidad);
        repuesto.setId(repuesto_id);
        repuestoRepository.save(repuesto);
    }
}
