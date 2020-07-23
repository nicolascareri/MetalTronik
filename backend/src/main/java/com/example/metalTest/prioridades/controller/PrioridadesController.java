package com.example.metalTest.prioridades.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.prioridades.controller.request.PrioridadesRequest;
import com.example.metalTest.prioridades.domain.Prioridades;
import com.example.metalTest.prioridades.mapper.PrioridadesMapper;
import com.example.metalTest.prioridades.service.PrioridadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/prioridades")
public class PrioridadesController {

    @Autowired
    PrioridadesService prioridadesService;

    @Autowired
    PrioridadesMapper prioridadesMapper;

    @GetMapping
    public ResponseEntity<List<Prioridades>> getAll() {
        return new ResponseEntity<>(prioridadesService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prioridades> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(prioridadesService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prioridades> create(@RequestBody PrioridadesRequest prioridadesRequest) throws ValidateFieldException {
        return new ResponseEntity<>(prioridadesService.create(prioridadesMapper.prioridadesRequestToPrioridades(prioridadesRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prioridades> update(@RequestBody PrioridadesRequest prioridadesRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(prioridadesService.update(prioridadesMapper.prioridadesRequestToPrioridades(prioridadesRequest), id), HttpStatus.OK);
    }
}
