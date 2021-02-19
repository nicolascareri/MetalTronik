package com.example.metalTest.correctivo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.correctivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.correctivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.correctivo.mapper.MantenimientoCorrectivoMapper;
import com.example.metalTest.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.correctivo.service.MantenimientoCorrectivoService;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MantenimientoCorrectivoServiceImpl implements MantenimientoCorrectivoService {

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    @Autowired
    MantenimientoCorrectivoMapper mantenimientoCorrectivoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    ParteRepository parteRepository;

    ParteBuscador parteBuscador = new ParteBuscador();

    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<MantenimientoCorrectivoResponse> getAll() {
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponseList(mantenimientoCorrectivoRepository.findAll());
    }

    @Override
    public MantenimientoCorrectivoResponse create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException {
        Integer maquina_id = mantenimientoCorrectivoRequest.getMaquina_id();
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);
        mantenimientoCorrectivo.setTipo((Tipo) repositoryValidator.getObject(tipoRepository, mantenimientoCorrectivoRequest.getTipo_id()));
        mantenimientoCorrectivo.setMaquina((Maquina) repositoryValidator.getObject(maquinaRepository, maquina_id));
        mantenimientoCorrectivo.setEncargo1((Personal)repositoryValidator.getObject(personalRepository, mantenimientoCorrectivoRequest.getEncargo1_id()));
        mantenimientoCorrectivo.setParte(parteBuscador.getParte(mantenimientoCorrectivoRequest.getParte_id(), parteRepository.getAllByMaquina(maquina_id)));
        mantenimientoCorrectivo.setEncargo2(this.getIfExist(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_id())));
        mantenimientoCorrectivo.setEncargo3(this.getIfExist(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_id())));
        mantenimientoCorrectivo.setOrdenTrabajo((OrdenesTrabajo) repositoryValidator.getObject(ordenesTrabajoRepository, mantenimientoCorrectivoRequest.getOrdenTrabajo_id()));
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }
        mantenimientoCorrectivo.setTiempoReparacion(getTiempoReparacion(mantenimientoCorrectivoRequest.getFechaFin(), mantenimientoCorrectivoRequest.getFechainicio()));
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    private Personal getIfExist(Optional opt){
        if (opt.isPresent()) {
            return (Personal) opt.get();
        }else{
            return null;
        }
    }

    @Override
    public MantenimientoCorrectivoResponse update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException {
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }
        Integer maquina_id = mantenimientoCorrectivoRequest.getMaquina_id();
        repositoryValidator.getObject(mantenimientoCorrectivoRepository, id);
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);
        mantenimientoCorrectivo.setMaquina((Maquina) repositoryValidator.getObject(maquinaRepository, maquina_id));
        OrdenesTrabajo ordenesTrabajo = (OrdenesTrabajo) repositoryValidator.getObject(ordenesTrabajoRepository, mantenimientoCorrectivoRequest.getOrdenTrabajo_id());
        mantenimientoCorrectivo.setParte(parteBuscador.getParte(mantenimientoCorrectivoRequest.getParte_id(), parteRepository.getAllByMaquina(maquina_id)));
        mantenimientoCorrectivo.setEncargo2(this.getIfExist(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_id())));
        mantenimientoCorrectivo.setEncargo3(this.getIfExist(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_id())));
        if (ordenesTrabajo != null ) {
            ordenesTrabajo.setEstado(Estado.OK);
        }
        mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        mantenimientoCorrectivo.setTiempoReparacion(getTiempoReparacion(mantenimientoCorrectivoRequest.getFechaFin(), mantenimientoCorrectivoRequest.getFechainicio()));
        mantenimientoCorrectivo.setId(id);
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    @Override
    public MantenimientoCorrectivoResponse getById(Integer id) throws ValidateFieldException {
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse((MantenimientoCorrectivo) repositoryValidator.getObject(mantenimientoCorrectivoRepository, id));
    }


    private int getTiempoReparacion(Date inicio, Date fin){
        long diffInMillies = Math.abs(fin.getTime() - inicio.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int)diff;
    }


}
