package com.example.metalTest.preventivo.tarea.tareas.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.preventivo.tarea.tareas.controller.request.TareasRequest;
import com.example.metalTest.preventivo.tarea.tareas.service.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarea")
public class TareasController {
    @Autowired
    TareasService tareasService;

    @GetMapping
    public ResponseEntity<List<TareasResponse>> getAll(){
        return new ResponseEntity<>(tareasService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareasResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tareasService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareasResponse> update(@PathVariable Integer id, @RequestBody TareasRequest tareasRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tareasService.update(id, tareasRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareasResponse> create(@RequestBody TareasRequest tareasRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tareasService.create(tareasRequest), HttpStatus.CREATED);
    }

}
