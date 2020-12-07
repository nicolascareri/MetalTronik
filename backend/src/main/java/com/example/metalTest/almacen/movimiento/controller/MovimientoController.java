package com.example.metalTest.almacen.movimiento.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.almacen.movimiento.domain.Movimiento;
import com.example.metalTest.almacen.movimiento.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAll(){
        return new ResponseEntity<>(movimientoService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movimiento> create(@Valid @RequestBody MovimientoRequest movimientoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(movimientoService.create(movimientoRequest), HttpStatus.CREATED);
    }

}
