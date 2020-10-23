package com.example.metalTest.parte.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.parte.controller.request.ParteRequest;
import com.example.metalTest.parte.controller.response.ParteResponse;
import com.example.metalTest.parte.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/parte")
public class ParteController {
    @Autowired
    ParteService parteService;

    @GetMapping
    public ResponseEntity<List<ParteResponse>> getAll(){
        return new ResponseEntity<>(parteService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParteResponse> create(@RequestBody @Valid ParteRequest parteRequest) throws ValidateFieldException {
        return new ResponseEntity<>(parteService.create(parteRequest), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParteResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(parteService.getById(id), HttpStatus.OK);
    }
    @PutMapping("/maquina/{id}")
    public ResponseEntity<List<ParteResponse>> vincular(@PathVariable Integer id, @RequestBody List<Integer> parteList) throws ValidateFieldException {
        return new ResponseEntity<>(parteService.vincular(id, parteList), HttpStatus.OK);
    }
    @GetMapping("/maquina/{id}")
    public ResponseEntity<List<ParteResponse>> getAllByMaquina(@PathVariable Integer id){
        return new ResponseEntity<>(parteService.findAllByMaquina(id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ValidateFieldException {
        parteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
