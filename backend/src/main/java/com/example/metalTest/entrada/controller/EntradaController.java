package com.example.metalTest.entrada.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.entrada.controller.request.EntradaRequest;
import com.example.metalTest.entrada.domain.Entrada;
import com.example.metalTest.entrada.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entrada")
public class EntradaController {

    @Autowired
    EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<Entrada>> getAll(){
        return new ResponseEntity<>(entradaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrada> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(entradaService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Entrada> create(@Valid @RequestBody EntradaRequest entradaRequest){
        return new ResponseEntity<>(entradaService.create(entradaRequest),HttpStatus.CREATED);
    }
}
