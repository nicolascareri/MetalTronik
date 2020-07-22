package com.example.metalTest.planta.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.planta.controller.request.PlantaRequest;
import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.planta.mapper.PlantaMapper;
import com.example.metalTest.planta.service.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/planta")
public class PlantaController {
    @Autowired
    PlantaService plantaService;

    @Autowired
    PlantaMapper plantaMapper;

    @GetMapping
    public ResponseEntity<List<Planta>> getAll() {
        return new ResponseEntity<>(plantaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planta> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(plantaService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Planta> create(@RequestBody PlantaRequest plantaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(plantaService.create(plantaMapper.plantaRequestToPlanta(plantaRequest)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Planta> update(@RequestBody PlantaRequest plantaRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(plantaService.update(plantaMapper.plantaRequestToPlanta(plantaRequest), id), HttpStatus.OK);
    }
}
