package com.example.metalTest.mantenimientos.preventivo.tarea.tareas.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.domain.Tareas;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import com.example.metalTest.mantenimientos.preventivo.tarea.historial.service.TareaHistorialService;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.controller.request.TareasRequest;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.mapper.TareasMapper;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.repository.TareasRepository;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.service.TareasService;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareasServiceImpl implements TareasService {
    @Autowired
    TareasRepository tareasRepository;
    @Autowired
    TareasMapper tareasMapper;
    @Autowired
    MaquinaRepository maquinaRepository;
    @Autowired
    ParteRepository parteRepository;
    @Autowired
    TareaHistorialService tareaHistorialService;
    @Autowired
    PersonalRepository personalRepository;

    RepositoryValidator<Tareas> repositoryValidator = new RepositoryValidator<Tareas>();

    ParteBuscador parteBuscador = new ParteBuscador();

    @Override
    public List<TareasResponse> getAll() {
        return tareasMapper.toTareaResponseList(tareasRepository.findAll());
    }

    @Override
    public TareasResponse getById(Integer id) throws ValidateFieldException {
        Tareas tarea = repositoryValidator.getObject(tareasRepository, id);
        return tareasMapper.toTareaResponse(tarea);
    }

    @Override
    public TareasResponse update(Integer id, TareasRequest tareasRequest) throws ValidateFieldException {
        Tareas tarea = repositoryValidator.getObject(tareasRepository, id);
        Integer maquina_id = tareasRequest.getMaquina_id();
        tareaHistorialService.create(tarea);
        tarea.setFrecuencia(tareasRequest.getFrecuencia());
        tarea.setInicio(tareasRequest.getInicio());
        RepositoryValidator<Maquina> repositoryValidator = new RepositoryValidator<Maquina>();
        Maquina maquina = repositoryValidator.getObject(maquinaRepository, tareasRequest.getMaquina_id());
        tarea.setMaquina(maquina);
        tarea.setParte(parteBuscador.getParte(tareasRequest.getParte_id(),parteRepository.getAllByMaquina(maquina_id)));
        tarea.setTarea(tareasRequest.getTarea());
        return tareasMapper.toTareaResponse(tareasRepository.save(tarea));
    }

    @Override
    public TareasResponse create(TareasRequest tareasRequest) throws ValidateFieldException {
        Tareas tarea = tareasMapper.tareaRequestToTarea(tareasRequest);
        Integer maquina_id = tareasRequest.getMaquina_id();
        RepositoryValidator<Maquina> repositoryValidator = new RepositoryValidator<Maquina>();
        Maquina maquina = repositoryValidator.getObject(maquinaRepository, tareasRequest.getMaquina_id());
        tarea.setMaquina(maquina);
        tarea.setParte(parteBuscador.getParte(tareasRequest.getParte_id(), parteRepository.getAllByMaquina(maquina_id)));
        return tareasMapper.toTareaResponse(tareasRepository.save(tarea));
    }
}