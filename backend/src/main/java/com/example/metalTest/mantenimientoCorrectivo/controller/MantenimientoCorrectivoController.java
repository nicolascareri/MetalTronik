package com.example.metalTest.mantenimientoCorrectivo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.mantenimientoCorrectivo.service.MantenimientoCorrectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mantenimiento-correctivo")
public class MantenimientoCorrectivoController {

    @Autowired
    MantenimientoCorrectivoService mantenimientoCorrectivoService;

    @GetMapping
    public ResponseEntity<List<MantenimientoCorrectivo>> getAll() {
        return new ResponseEntity<>(mantenimientoCorrectivoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoCorrectivo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoCorrectivo> update(@Valid @RequestBody MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.update(mantenimientoCorrectivoRequest, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MantenimientoCorrectivo> create(@Valid @RequestBody MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.create(mantenimientoCorrectivoRequest), HttpStatus.CREATED);
    }


}
