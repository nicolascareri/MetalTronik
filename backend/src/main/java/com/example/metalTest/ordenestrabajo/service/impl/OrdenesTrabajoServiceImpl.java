package com.example.metalTest.ordenestrabajo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.mantenimientoCorrectivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import com.example.metalTest.parte.domain.Parte;
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

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;


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
        ordenesTrabajo.setMaquina(maquinaRepository.findById(ordenesTrabajoRequest.getMaquina_id()).get());
        ordenesTrabajo.setParte(this.setParte(ordenesTrabajoRequest.getParte_id(),ordenesTrabajoRequest.getMaquina_id()));
        ordenesTrabajo.setEncargo(usuarioRepository.findById(ordenesTrabajoRequest.getEncargo_cod()).get());
        ordenesTrabajo.setResponsable(usuarioRepository.findById(ordenesTrabajoRequest.getResponsable_cod()).get());
        ordenesTrabajo.setTipo(tipoRepository.findById(ordenesTrabajoRequest.getTipo_cod()).get());
        ordenesTrabajo.setPrioridad(prioridadesRepository.findById(ordenesTrabajoRequest.getPrioridad_cod()).get());
        ordenesTrabajo.setEstado(EstadoOrden.PENDIENTE.getValue());
        return ordenesTrabajo;
    }

    /**
     * Verifica que el campo "parteId" exista, si asi es lo busca en la base de datos
     * @param parteId
     * @return null(si parteId es null o si no lo encuentra en la base) o Parte
     */
    private Parte setParte(Integer parteId, Integer maquinaId){
        if(parteId != null){
            return findParte(maquinaId, parteId);
        }
        return null;
    }

    @Override
    public OrdenesTrabajoResponse update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException {
        Optional<OrdenesTrabajo> opt = ordenesTrabajoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La orden de trabajo a la que intenta acceder no existe", "id", String.valueOf(id));
        }
        OrdenesTrabajo ordenesTrabajo = opt.get();
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
        ordenesTrabajo.setParte(updateParte(ordenesTrabajo, ordenesTrabajoRequest.getParte_id()));
        if (ordenesTrabajoRequest.getFechaEntrega().after(ordenesTrabajoRequest.getFechaRealizar())) {
            throw new ValidateFieldException("La fecha de entrega no puede ser menor que la fecha de realizar", "Fecha de entrega", String.valueOf(ordenesTrabajoRequest.getFechaRealizar()));
        }
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse(ordenesTrabajoRepository.save(ordenesTrabajo));
    }

    private Parte updateParte(OrdenesTrabajo ordenesTrabajo, Integer parteId) {
        if(parteId == null)
            return null;
        else{
            return findParte(ordenesTrabajo.getMaquina().getId(), parteId);
        }

    }
    private Parte findParte(Integer maquinaId, Integer parteId){
        List<Parte> parteList = parteRepository.getAllByMaquina(maquinaId);
        if (!parteList.isEmpty()){
            for (Parte a: parteList
            ) {
                if(a.getId() == parteId) return a;
            }
        }
        return null;
    }


}
