package com.example.metalTest.maquina.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.maquina.service.MaquinaService;
import com.example.metalTest.parte.controller.response.ParteResponse;
import com.example.metalTest.parte.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/maquina")
public class MaquinaController {

    @Autowired
    MaquinaService maquinaService;

    @Autowired
    MaquinaMapper maquinaMapper;
    @Autowired
    ParteService parteService;

    @GetMapping
    public ResponseEntity<List<MaquinaReducidoResponse>> getAll() {
        return new ResponseEntity<>(maquinaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinaResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MaquinaResponse> create(@Valid @RequestBody MaquinaRequest maquinaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.save(maquinaRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaquinaResponse> update(@Valid @RequestBody MaquinaRequest maquinaRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.update(maquinaRequest, id), HttpStatus.OK);
    }

    @PutMapping("/vincular/{id}")
    public ResponseEntity<List<ParteResponse>> vincular(@PathVariable Integer id, @RequestBody List<Integer> parteList) throws ValidateFieldException {
        return new ResponseEntity<>(parteService.vincular(id, parteList), HttpStatus.OK);
    }
    @GetMapping("/partesByIdMaquina/{id}")
    public ResponseEntity<List<ParteResponse>> getAllByMaquina(@PathVariable Integer id){
        return new ResponseEntity<>(parteService.findAllByMaquina(id),HttpStatus.OK);
    }

}
