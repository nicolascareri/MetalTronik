package com.example.metalTest.tipo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo/")
public class TipoController {

    @Autowired
    TipoService tipoService;


    @GetMapping
    public ResponseEntity<List<Tipo>> getAll() {
        return new ResponseEntity<>(tipoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tipo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tipoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tipo> create(@RequestBody TipoRequest tipoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tipoService.create(tipoRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tipo> update(@RequestBody TipoRequest tipoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tipoService.update(tipoRequest, id), HttpStatus.OK);
    }

    @GetMapping("nombre/{tipo}")
    public ResponseEntity<List<Tipo>> getByTipo(@PathVariable String tipo){
        return new ResponseEntity<>(tipoService.getByTipo(tipo), HttpStatus.OK);
    }
    @GetMapping("tipos")
    public ResponseEntity<List<String>> getTipos(){
        return new ResponseEntity<>(tipoService.getTipos(), HttpStatus.OK);
    }
}
