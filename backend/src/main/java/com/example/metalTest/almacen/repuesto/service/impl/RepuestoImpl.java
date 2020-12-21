package com.example.metalTest.almacen.repuesto.service.impl;

import com.example.metalTest.almacen.repuesto.controller.request.AsociarList;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoAsociarRequest;
import com.example.metalTest.almacen.repuesto.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.repuesto.domain.Asociacion;
import com.example.metalTest.almacen.repuesto.repository.AsociacionRepository;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.repuesto.service.RepuestoService;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoImpl implements RepuestoService {

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
    public List<Repuesto> getAll() {
        List<Repuesto> a = repuestoRepository.findAll();
        return a;
    }

    @Override
    public RepuestoResponse getById(Integer id) throws ValidateFieldException {
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        return repuestoMapper.toRepuestoResponse(opt.get());
    }


    @Override
    public Repuesto create(RepuestoRequest repuestoRequest) {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        repuesto.setCantidad_instalada(0);
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException {
        Repuesto repuesto = repuestoMapper.repuestoRequestToRepuesto(repuestoRequest);
        Optional<Repuesto> opt = repuestoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El repuesto que desea acceder no existe", "id", String.valueOf(id));
        }
        repuesto.setId(id);
        return repuestoRepository.save(repuesto);
    }

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

    private void updateRepuesto(Repuesto repuesto, Integer cantidad, Integer repuesto_id){
        repuesto.setCantidad_instalada(repuesto.getCantidad_instalada() + cantidad);
        repuesto.setId(repuesto_id);
        repuestoRepository.save(repuesto);
    }
    @Override
    public List<Asociacion> getVinculados() {
        return asociacionRepository.findAll();
    }

    @Override
    public List<Maquina> getSinVincular() {
        return asociacionRepository.getSinAsociar();
    }

}
