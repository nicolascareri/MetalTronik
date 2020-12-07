package com.example.metalTest.ordenestrabajo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
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

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;
    ParteBuscador parteBuscador = new ParteBuscador();


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
        if (ordenesTrabajoRequest.getFechaEntrega().after(ordenesTrabajoRequest.getFechaRealizar())) {
            throw new ValidateFieldException("La fecha de entrega no puede ser menor que la fecha de realizar", "Fecha de entrega", String.valueOf(ordenesTrabajoRequest.getFechaRealizar()));
        }
        OrdenesTrabajo newOrdenesTrabajo = setOrdenesTrabajo(ordenesTrabajoRequest);
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse(ordenesTrabajoRepository.save(newOrdenesTrabajo));
    }

    /**
     * Toma los valores del parametro y setea una nueva orden de trabajo
     * Toma el id de la maquina y la busca en la base al igual que la parte(la cual es opcional)
     * @param ordenesTrabajoRequest json enviado del frontend
     * @return orden de trabajo seteada
     */
    private OrdenesTrabajo setOrdenesTrabajo(OrdenesTrabajoRequest ordenesTrabajoRequest){
        OrdenesTrabajo ordenesTrabajo = new OrdenesTrabajo();
        Integer maquinaCod = ordenesTrabajoRequest.getMaquina_id();
        ordenesTrabajo.setMaquina(maquinaRepository.findById(maquinaCod).get());
        ordenesTrabajo.setParte(parteBuscador.getParte(ordenesTrabajoRequest.getParte_id(), parteRepository.getAllByMaquina(maquinaCod)));
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setTipo(tipoRepository.findById(ordenesTrabajoRequest.getTipo_cod()).get());
        ordenesTrabajo.setPrioridad(prioridadesRepository.findById(ordenesTrabajoRequest.getPrioridad_cod()).get());
        ordenesTrabajo.setEstado(EstadoOrden.PENDIENTE.getValue());
        ordenesTrabajo.setFechaEntrega(ordenesTrabajoRequest.getFechaEntrega());
        ordenesTrabajo.setFechaRealizar(ordenesTrabajoRequest.getFechaRealizar());
        ordenesTrabajo.setObservaciones(ordenesTrabajoRequest.getObservaciones());
        ordenesTrabajo.setPedidoMateriales(ordenesTrabajoRequest.getPedidoMateriales());
        ordenesTrabajo.setTarea(ordenesTrabajoRequest.getTarea());
        return ordenesTrabajo;
    }



    @Override
    public OrdenesTrabajoResponse update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La orden de trabajo a la que intenta acceder no existe", "id", String.valueOf(id));
        }
        OrdenesTrabajo ordenesTrabajo = opt.get();
        Integer maquinaCod=ordenesTrabajo.getMaquina().getId();
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
        ordenesTrabajo.setEstado(ordenesTrabajoRequest.getEstado());
        ordenesTrabajo.setMaquina(maquinaRepository.findById(ordenesTrabajoRequest.getMaquina_id()).get());
        ordenesTrabajo.setParte(parteBuscador.getParte(ordenesTrabajoRequest.getParte_id(),parteRepository.getAllByMaquina(maquinaCod) ));
        if (ordenesTrabajoRequest.getFechaEntrega().after(ordenesTrabajoRequest.getFechaRealizar())) {
            throw new ValidateFieldException("La fecha de entrega no puede ser menor que la fecha de realizar", "Fecha de entrega", String.valueOf(ordenesTrabajoRequest.getFechaRealizar()));
        }
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse(ordenesTrabajoRepository.save(ordenesTrabajo));
    }





}
