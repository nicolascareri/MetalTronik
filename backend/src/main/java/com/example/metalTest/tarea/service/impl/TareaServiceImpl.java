package com.example.metalTest.tarea.service.impl;

import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;
import com.example.metalTest.tarea.mapper.TareaMapper;
import com.example.metalTest.tarea.repository.TareaRepository;
import com.example.metalTest.tarea.service.TareaService;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaMapper tareaMapper;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Tarea> getAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea create(TareaRequest tareaRequest) {

        Tarea tarea = tareaMapper.tareaRequestToTarea(tareaRequest);

        tarea.setUsuario(usuarioRepository.findById(tareaRequest.getUsuario_cod()).get());

        tarea.setFechaAlta(new Date(System.currentTimeMillis()));
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> getByUsuario(Integer id) {
        return tareaRepository.findByUsuario(id);
    }


}
