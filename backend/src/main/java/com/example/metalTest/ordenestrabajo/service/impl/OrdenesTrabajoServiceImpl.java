package com.example.metalTest.ordenestrabajo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenesTrabajoServiceImpl implements OrdenesTrabajoService {

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;

    @Autowired
    OrdenesTrabajoMapper ordenesTrabajoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SectorRepository sectorRepository;




    @Override
    public List<OrdenesTrabajo> getAll() {
        return ordenesTrabajoRepository.findAll();
    }

    @Override
    public OrdenesTrabajo getById(Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        else{
            throw new ValidateFieldException("La orden de trabajo que desea acceder no existe", "id", String.valueOf(id));
        }
    }

    @Transactional
    @Override
    public OrdenesTrabajo create(OrdenesTrabajoRequest ordenesTrabajoRequest) {
        OrdenesTrabajo ordenesTrabajo = ordenesTrabajoMapper.ordenesTrabajoRequestToOrdenesTrabajo(ordenesTrabajoRequest);
        ordenesTrabajo.setMaquina(maquinaRepository.getByCod(ordenesTrabajoRequest.getMaquina_cod()));
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setSector(sectorRepository.findById(ordenesTrabajoRequest.getSector_cod()).get());
        return ordenesTrabajoRepository.save(ordenesTrabajo);
    }

    @Override
    public OrdenesTrabajo update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if(!opt.isPresent()){
            throw new ValidateFieldException("La orden de trabajo a la que intenta acceder no existe", "id", String.valueOf(id));
        }
        OrdenesTrabajo ordenesTrabajo = opt.get();
        ordenesTrabajo.setPlanta(ordenesTrabajoRequest.getPlanta());
        ordenesTrabajo.setMaquina(maquinaRepository.getByCod(ordenesTrabajoRequest.getMaquina_cod()));
        ordenesTrabajo.setPedidoMateriales(ordenesTrabajoRequest.getPedidoMateriales());
        ordenesTrabajo.setTarea(ordenesTrabajoRequest.getTarea());
        ordenesTrabajo.setPriodidad(ordenesTrabajoRequest.getPriodidad());
        ordenesTrabajo.setTipo(ordenesTrabajoRequest.getTipo());
        ordenesTrabajo.setFechaEntrega(ordenesTrabajoRequest.getFechaEntrega());
        ordenesTrabajo.setFechaRealizar(ordenesTrabajoRequest.getFechaRealizar());
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setEstado(ordenesTrabajoRequest.getEstado());
        ordenesTrabajo.setSector(sectorRepository.findById(ordenesTrabajoRequest.getSector_cod()).get());
        ordenesTrabajo.setObservaciones(ordenesTrabajoRequest.getObservaciones());
        ordenesTrabajo.setOrdenTerciarizacion(ordenesTrabajoRequest.getOrdenTerciarizacion());
        return ordenesTrabajoRepository.save(ordenesTrabajo);
    }
}
