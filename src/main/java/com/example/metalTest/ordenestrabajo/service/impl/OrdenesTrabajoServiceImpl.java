package com.example.metalTest.ordenestrabajo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.mantenimientos.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenesTrabajoServiceImpl implements OrdenesTrabajoService {

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;

    @Autowired
    OrdenesTrabajoMapper ordenesTrabajoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    ParteRepository parteRepository;


    RepositoryValidator<OrdenesTrabajo> repositoryValidator = new RepositoryValidator<OrdenesTrabajo>();

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;
    ParteBuscador parteBuscador = new ParteBuscador();


    @Override
    public List<OrdenesTrabajoResponse> getAll() {
        return ordenesTrabajoMapper.toOrdenesTrabajoResponseList(ordenesTrabajoRepository.findAll());
    }

    @Override
    public OrdenesTrabajoResponse getById(Integer id) throws ValidateFieldException {
        return ordenesTrabajoMapper.toOrdenesTrabajoResponse( repositoryValidator.getObject(ordenesTrabajoRepository, id));
    }

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
    private OrdenesTrabajo setOrdenesTrabajo(OrdenesTrabajoRequest ordenesTrabajoRequest) throws ValidateFieldException {
        OrdenesTrabajo ordenesTrabajo = new OrdenesTrabajo();
        Integer maquina_id = ordenesTrabajoRequest.getMaquina_id();
        RepositoryValidator<Maquina> maquinaRepositoryValidator = new  RepositoryValidator<Maquina>();
        ordenesTrabajo.setMaquina( maquinaRepositoryValidator.getObject(maquinaRepository, maquina_id));
        ordenesTrabajo.setParte(parteBuscador.getParte(ordenesTrabajoRequest.getParte_id(), parteRepository.getAllByMaquina(maquina_id)));

        RepositoryValidator<Personal> personalRepositoryValidator = new  RepositoryValidator<Personal>();
        ordenesTrabajo.setEncargo(personalRepositoryValidator.getObject(personalRepository, ordenesTrabajoRequest.getEncargo_id()));
        ordenesTrabajo.setResponsable(personalRepositoryValidator.getObject(personalRepository, ordenesTrabajoRequest.getResponsable_id()));

        RepositoryValidator<Tipo> tipoRepositoryValidator = new RepositoryValidator<>();
        ordenesTrabajo.setTipo(tipoRepositoryValidator.getObject(tipoRepository, ordenesTrabajoRequest.getTipo_id()));
        ordenesTrabajo.setPrioridad(tipoRepositoryValidator.getObject(tipoRepository, ordenesTrabajoRequest.getPrioridad_id()));
        ordenesTrabajo.setEstado(Estado.PENDIENTE);
        ordenesTrabajo.setFechaEntrega(ordenesTrabajoRequest.getFechaEntrega());
        ordenesTrabajo.setFechaRealizar(ordenesTrabajoRequest.getFechaRealizar());
        ordenesTrabajo.setObservaciones(ordenesTrabajoRequest.getObservaciones());
        ordenesTrabajo.setPedidoMateriales(ordenesTrabajoRequest.getPedidoMateriales());
        ordenesTrabajo.setTarea(ordenesTrabajoRequest.getTarea());
        return ordenesTrabajo;
    }



    @Override
    public OrdenesTrabajoResponse update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException {
        OrdenesTrabajo ordenesTrabajo = repositoryValidator.getObject(ordenesTrabajoRepository, id);
        Integer maquinaCod=ordenesTrabajo.getMaquina().getId();
        ordenesTrabajo.setPedidoMateriales(ordenesTrabajoRequest.getPedidoMateriales());
        ordenesTrabajo.setTarea(ordenesTrabajoRequest.getTarea());
        ordenesTrabajo.setPrioridad(tipoRepository.findById(ordenesTrabajoRequest.getPrioridad_id()).get());
        ordenesTrabajo.setTipo(tipoRepository.findById(ordenesTrabajoRequest.getTipo_id()).get());
        ordenesTrabajo.setFechaEntrega(ordenesTrabajoRequest.getFechaEntrega());
        ordenesTrabajo.setFechaRealizar(ordenesTrabajoRequest.getFechaRealizar());
        ordenesTrabajo.setEncargo(personalRepository.findById(ordenesTrabajoRequest.getEncargo_id()).get());
        ordenesTrabajo.setResponsable(personalRepository.findById(ordenesTrabajoRequest.getResponsable_id()).get());
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
