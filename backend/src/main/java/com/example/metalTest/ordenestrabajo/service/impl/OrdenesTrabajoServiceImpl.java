package com.example.metalTest.ordenestrabajo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.prioridades.repository.PrioridadesRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    PrioridadesRepository prioridadesRepository;

    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    ParteRepository parteRepository;

    protected ToIndicadoresConverter toIndicadoresConverter = new ToIndicadoresConverter();

    @Override
    public List<OrdenesTrabajoResponse> getAll() {
        return ordenesTrabajoMapper.toOrdenesTrabajoResponseList(ordenesTrabajoRepository.findAll());
    }

    @Override
    public OrdenesTrabajoResponse getById(Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if (opt.isPresent()) {
            return ordenesTrabajoMapper.toOrdenesTrabajoResponse(opt.get());
        } else {
            throw new ValidateFieldException("La orden de trabajo que desea acceder no existe", "id", String.valueOf(id));
        }
    }

    @Transactional
    @Override
    public OrdenesTrabajoResponse create(OrdenesTrabajoRequest ordenesTrabajoRequest) throws ValidateFieldException {
        OrdenesTrabajo ordenesTrabajo = ordenesTrabajoMapper.ordenesTrabajoRequestToOrdenesTrabajo(ordenesTrabajoRequest);
        setMaquinaOrParte(ordenesTrabajoRequest, ordenesTrabajo);
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setTipo(tipoRepository.findById(ordenesTrabajoRequest.getTipo_cod()).get());
        ordenesTrabajo.setPrioridad(prioridadesRepository.findById(ordenesTrabajoRequest.getPrioridad_cod()).get());
        ordenesTrabajo.setEstado(EstadoOrden.PENDIENTE.getValue());
        if (ordenesTrabajoRequest.getFechaEntrega().after(ordenesTrabajoRequest.getFechaRealizar())) {
            throw new ValidateFieldException("La fecha de entrega no puede ser menor que la fecha de realizar", "Fecha de entrega", String.valueOf(ordenesTrabajoRequest.getFechaRealizar()));
        }
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse(ordenesTrabajoRepository.save(ordenesTrabajo));
    }

    @Override
    public OrdenesTrabajoResponse update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La orden de trabajo a la que intenta acceder no existe", "id", String.valueOf(id));
        }
        OrdenesTrabajo ordenesTrabajo = opt.get();
        setMaquinaOrParte(ordenesTrabajoRequest, ordenesTrabajo);
        ordenesTrabajo.setPedidoMateriales(ordenesTrabajoRequest.getPedidoMateriales());
        ordenesTrabajo.setTarea(ordenesTrabajoRequest.getTarea());
        ordenesTrabajo.setPrioridad(prioridadesRepository.findById(ordenesTrabajoRequest.getPrioridad_cod()).get());
        ordenesTrabajo.setTipo(tipoRepository.findById(ordenesTrabajoRequest.getTipo_cod()).get());
        ordenesTrabajo.setFechaEntrega(ordenesTrabajoRequest.getFechaEntrega());
        ordenesTrabajo.setFechaRealizar(ordenesTrabajoRequest.getFechaRealizar());
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setObservaciones(ordenesTrabajoRequest.getObservaciones());
        ordenesTrabajo.setOrdenTerciarizacion(ordenesTrabajoRequest.getOrdenTerciarizacion());
        if (ordenesTrabajoRequest.getFechaEntrega().after(ordenesTrabajoRequest.getFechaRealizar())) {
            throw new ValidateFieldException("La fecha de entrega no puede ser menor que la fecha de realizar", "Fecha de entrega", String.valueOf(ordenesTrabajoRequest.getFechaRealizar()));
        }
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse(ordenesTrabajoRepository.save(ordenesTrabajo));
    }


    private void setMaquinaOrParte(OrdenesTrabajoRequest ordenesTrabajoRequest, OrdenesTrabajo ordenesTrabajo) throws ValidateFieldException {
        if (parteRepository.existsById(ordenesTrabajoRequest.getParteOrMaquina())) {
            ordenesTrabajo.setParte(parteRepository.getOne(ordenesTrabajoRequest.getParteOrMaquina()));
        } else if (maquinaRepository.existsById(ordenesTrabajoRequest.getParteOrMaquina())){
            ordenesTrabajo.setMaquina(maquinaRepository.getOne(ordenesTrabajoRequest.getParteOrMaquina()));
        } else {
            throw new ValidateFieldException("La parte o maquina que desea acceder no existe","id",String.valueOf(ordenesTrabajoRequest.getParteOrMaquina()));
        }
    }

    @Override
    public List<IndicatorResponse> getIndicatorsUsuario() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoByUsuarios(EstadoOrden.OK.getValue(), EstadoOrden.PENDIENTE.getValue());
        return toIndicadoresConverter.getIndicadores(toParse);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsSector() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoBySectores(EstadoOrden.OK.getValue(), EstadoOrden.PENDIENTE.getValue());
        System.out.println(toParse);

        //toIndicadoresConverter.getIndicadores(toParse)
        return null;
    }


}
