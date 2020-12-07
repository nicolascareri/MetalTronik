package com.example.metalTest.preventivo.tarea.tareas.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import com.example.metalTest.preventivo.tarea.historial.service.TareaHistorialService;
import com.example.metalTest.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.preventivo.tarea.tareas.controller.request.TareasRequest;
import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import com.example.metalTest.preventivo.tarea.tareas.mapper.TareasMapper;
import com.example.metalTest.preventivo.tarea.tareas.repository.TareasRepository;
import com.example.metalTest.preventivo.tarea.tareas.service.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    ParteBuscador parteBuscador = new ParteBuscador();

    @Override
    public List<TareasResponse> getAll() {
        return tareasMapper.toTareaResponseList(tareasRepository.findAll());
    }

    @Override
    public TareasResponse getById(Integer id) throws ValidateFieldException {
        Optional<Tareas> optionalTarea = tareasRepository.findById(id);
        if (!optionalTarea.isPresent()){
            throw new ValidateFieldException("La tareas que intenta acceder no existe", "id", String.valueOf(id));
        }
        return tareasMapper.toTareaResponse(optionalTarea.get());
    }

    @Override
    public TareasResponse update(Integer id, TareasRequest tareasRequest) throws ValidateFieldException {
        Optional<Tareas> optionalTarea = tareasRepository.findById(id);
        if (!optionalTarea.isPresent()){
            throw new ValidateFieldException("La tareas que intenta acceder no existe", "id", String.valueOf(id));
        }
        if (tareasRequest.getEstado() != Estado.ACTIVO.getValue() && tareasRequest.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tareasRequest.getEstado()));
        }

        Integer maquinaCod = tareasRequest.getMaquina_cod();
        Tareas tarea = optionalTarea.get();
        tareaHistorialService.create(tarea);
        tarea.setEstado(tareasRequest.getEstado());
        tarea.setFrecuencia(tareasRequest.getFrecuencia());
        tarea.setInicio(tareasRequest.getInicio());
        tarea.setMaquina(maquinaRepository.findById(maquinaCod).get());
        tarea.setParte(parteBuscador.getParte(tareasRequest.getParte_cod(),parteRepository.getAllByMaquina(maquinaCod)));
        tarea.setTarea(tareasRequest.getTarea());
        return tareasMapper.toTareaResponse(tareasRepository.save(tarea));
    }

    @Override
    public TareasResponse create(TareasRequest tareasRequest) throws ValidateFieldException {
        if (tareasRequest.getEstado() != Estado.ACTIVO.getValue() && tareasRequest.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tareasRequest.getEstado()));
        }
        Tareas tarea = tareasMapper.tareaRequestToTarea(tareasRequest);
        Integer maquinaCod = tareasRequest.getMaquina_cod();
        tarea.setMaquina(maquinaRepository.findById(maquinaCod).get());
        tarea.setParte(parteBuscador.getParte(tareasRequest.getParte_cod(), parteRepository.getAllByMaquina(maquinaCod)));
        return tareasMapper.toTareaResponse(tareasRepository.save(tarea));
    }
}