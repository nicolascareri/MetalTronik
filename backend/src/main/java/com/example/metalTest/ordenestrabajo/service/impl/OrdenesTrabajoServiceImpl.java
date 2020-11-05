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

import java.util.ArrayList;
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
    public List<IndicatorResponse> getIndicators() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoByEstado(EstadoOrden.OK.getValue(), EstadoOrden.PENDIENTE.getValue());
        return getIndicadores(toParse);
    }
    private List<IndicatorResponse> getIndicadores(List<String> ordenes){
        List<IndicatorResponse> indicatorResponses = new ArrayList<>();
        for (String[] usuario: getOrdenesList(ordenes)
        ) {
            IndicatorResponse indicador = new IndicatorResponse();
            indicador.setLabel(usuario[0]);
            Integer ok =Integer.parseInt(usuario[1]);
            Integer pendiente = Integer.parseInt(usuario[2]);
            //pregunto si pendiente es 0 porque tal vez no lo tenga
            //no se puede dividir por 0
            if(pendiente == 0){
                pendiente++;
            }
            Integer resultadoFormula = (ok /pendiente ) * 100;
            System.out.println("1: "+Integer.parseInt(usuario[1]) + "2: "+Integer.parseInt(usuario[2]));
            List<Integer> data = new ArrayList<>();
            data.add(resultadoFormula);
            indicador.setData(data);
            indicatorResponses.add(indicador);

        }
        return indicatorResponses;
    }

    /**
     * Parsea un string, devuelve un array de string
     * @param toParse String a parsear
     * @return array de strings parseados
     */
    private String[] parser(String toParse){
        return toParse.split(",");
    }

    /**
     * Parsea segun la cantidad que posea la posicion del arreglo de ordenes
     * @param ordenes lista de ordenes traida de la db
     * @return retorna en cada posicion de la lista hay un arreglo de strings
     */
    private List<String[]> getOrdenesList(List<String> ordenes){
        List<String[]> parseados = new ArrayList<>();
        for(String toParse: ordenes){
            parseados.add(this.parser(toParse));
        }
        return parseados;
    }

}
