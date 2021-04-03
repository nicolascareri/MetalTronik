package com.example.metalTest.almacen.movimiento.salida.controller;

import com.example.metalTest.almacen.movimiento.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.movimiento.salida.controller.response.SalidaResponse;
import com.example.metalTest.almacen.movimiento.salida.service.SalidaService;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/salida")
public class SalidaController {
    @Autowired
    SalidaService salidaService;

    @GetMapping
    public ResponseEntity<List<SalidaResponse>> getAll(){
        return new ResponseEntity<>(salidaService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SalidaResponse> create(@Valid @RequestBody SalidaRequest salidaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(salidaService.create(salidaRequest), HttpStatus.CREATED);
    }
}
