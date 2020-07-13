package com.example.metalTest.repuestomaquina.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.repuestomaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;
import com.example.metalTest.repuestomaquina.service.RepuestoMaquinaService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/repuesto-maquina")
public class RepuestoMaquinaController {

    @Autowired
    RepuestoMaquinaService repuestoMaquinaService;

    @GetMapping
    public ResponseEntity<List<RepuestoMaquina>> getAll(){
        return new ResponseEntity<>(repuestoMaquinaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepuestoMaquina> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoMaquinaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/maquina/{id}")
    public ResponseEntity<RepuestoMaquina> getByMaquina(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoMaquinaService.getByMaquina(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RepuestoMaquina> create(@Valid @RequestBody RepuestoMaquinaRequest repuestoMaquinaRequest){
        return new ResponseEntity<>(repuestoMaquinaService.create(repuestoMaquinaRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RepuestoMaquina> update(@Valid @RequestBody RepuestoMaquinaRequest repuestoMaquinaRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoMaquinaService.update(repuestoMaquinaRequest, id), HttpStatus.OK);
    }

}