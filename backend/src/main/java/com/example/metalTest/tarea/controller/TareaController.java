package com.example.metalTest.tarea.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tarea.controller.Response.TareaResponse;
import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {
    @Autowired
    TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<TareaResponse>> getAll(){
        return new ResponseEntity<>(tareaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tareaService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> update(@PathVariable Integer id, @RequestBody TareaRequest tareaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tareaService.update(id, tareaRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareaResponse> create(@RequestBody TareaRequest tareaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tareaService.create(tareaRequest), HttpStatus.CREATED);
    }

}
