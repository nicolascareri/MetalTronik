package com.example.metalTest.tarea.controller;

import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;
import com.example.metalTest.tarea.mapper.TareaMapper;
import com.example.metalTest.tarea.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tarea")
public class TareaController {
    @Autowired
    TareaMapper tareaMapper;

    @Autowired
    TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> getAll(){
        return new ResponseEntity<>(tareaService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tarea> create(@RequestBody TareaRequest tareaRequest){
        return new ResponseEntity<Tarea>(tareaService.create(tareaRequest), HttpStatus.CREATED);
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tarea>>getByUsuario(@PathVariable Integer id){
        return new ResponseEntity<>(tareaService.getByUsuario(id), HttpStatus.OK);
    }


}
