package com.example.metalTest.almacen.repuesto.service.impl;

import com.example.metalTest.almacen.repuesto.controller.request.AsociarList;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoAsociarRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoVinculadoResponse;
import com.example.metalTest.almacen.repuesto.domain.CantidadInstalada;
import com.example.metalTest.almacen.repuesto.repository.CantInstaladaRepository;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoReducidoResponse;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.almacen.repuesto.service.RepuestoService;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    CantInstaladaRepository cantInstaladaRepository;

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
        for (RepuestoAsociarRequest ra: asociarList.getRequestList()) {
            Repuesto repuesto = repuestoRepository.findById(ra.getRepuesto_id()).get();
            repuesto.setMaquina(maquinaRepository.findById(asociarList.getMaquina_id()).get());
            repuesto.setParte(parteBuscador.getParte(asociarList.getParte_id(), parteRepository.getAllByMaquina(asociarList.getMaquina_id())));
            repuesto.setCantidadInstalada(new CantidadInstalada(ra.getCantidad_instalada()));
            repuesto.setId(ra.getRepuesto_id());
            repuestoRepository.save(repuesto);
        }
    }


    @Override
    public List<Repuesto> getByMaquina(Integer id){
        return repuestoRepository.findByMaquina(id);
    }

    @Override
    public List<RepuestoVinculadoResponse> getVinculados() {
        List<Repuesto> repuestos = repuestoRepository.getVinculados();
        List<RepuestoVinculadoResponse> repuestoVinculadoResponses = new ArrayList<>();
        for (Repuesto r : repuestos) {
            RepuestoVinculadoResponse rvr =repuestoMapper.repuestoToRepuestoVinculadoResponse(r);
            rvr.setCantidad_instalada(r.getCantidadInstalada().getCantidad_instalada());
            repuestoVinculadoResponses.add(rvr);
        }
        return repuestoVinculadoResponses;
    }

}
