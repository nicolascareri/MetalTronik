package com.example.metalTest.tarea.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.tarea.controller.Response.TareaResponse;
import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;
import com.example.metalTest.tarea.mapper.TareaMapper;
import com.example.metalTest.tarea.repository.TareaRepository;
import com.example.metalTest.tarea.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    TareaMapper tareaMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Override
    public List<TareaResponse> getAll() {
        return tareaMapper.toTareaResponseList(tareaRepository.findAll());
    }

    @Override
    public TareaResponse getById(Integer id) throws ValidateFieldException {
        Optional<Tarea> optionalTarea = tareaRepository.findById(id);
        if (!optionalTarea.isPresent()){
            throw new ValidateFieldException("La tarea que intenta acceder no existe", "id", String.valueOf(id));
        }
        return tareaMapper.toTareaResponse(optionalTarea.get());
    }

    @Override
    public TareaResponse update(Integer id, TareaRequest tareaRequest) throws ValidateFieldException {
        Optional<Tarea> optionalTarea = tareaRepository.findById(id);
        if (!optionalTarea.isPresent()){
            throw new ValidateFieldException("La tarea que intenta acceder no existe", "id", String.valueOf(id));
        }
        if (tareaRequest.getEstado() != Estado.ACTIVO.getValue() && tareaRequest.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tareaRequest.getEstado()));
        }
        Tarea tarea = optionalTarea.get();
        tarea.setEstado(tareaRequest.getEstado());
        tarea.setFrecuencia(tareaRequest.getFrecuencia());
        tarea.setInicio(tareaRequest.getInicio());
        tarea.setMaquina(maquinaRepository.findById(tareaRequest.getMaquina_cod()).get());
        tarea.setTarea(tareaRequest.getTarea());
        return tareaMapper.toTareaResponse(tareaRepository.save(tarea));
    }

    @Override
    public TareaResponse create(TareaRequest tareaRequest) throws ValidateFieldException {
        if (tareaRequest.getEstado() != Estado.ACTIVO.getValue() && tareaRequest.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(tareaRequest.getEstado()));
        }
        Tarea tarea = tareaMapper.tareaRequestToTarea(tareaRequest);
        tarea.setMaquina(maquinaRepository.findById(tareaRequest.getMaquina_cod()).get());
        return tareaMapper.toTareaResponse(tareaRepository.save(tarea));
    }
}
