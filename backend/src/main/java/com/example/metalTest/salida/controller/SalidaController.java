package com.example.metalTest.salida.controller;

import com.example.metalTest.salida.controller.request.SalidaRequest;
import com.example.metalTest.salida.domain.Salida;
import com.example.metalTest.salida.service.SalidaService;
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
    public ResponseEntity<List<Salida>> getAll(){
        return new ResponseEntity<>(salidaService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Salida> create(@Valid @RequestBody SalidaRequest salidaRequest){
        return new ResponseEntity<>(salidaService.create(salidaRequest), HttpStatus.CREATED);
    }
}
