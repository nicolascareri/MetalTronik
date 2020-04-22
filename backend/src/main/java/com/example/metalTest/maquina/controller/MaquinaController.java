package com.example.metalTest.maquina.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.maquina.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping
    public ResponseEntity<List<Maquina>> getAll(){
        return new ResponseEntity<>(maquinaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/codigo/{cod}")
    public ResponseEntity<Maquina> getByCod(@PathVariable String maquina_cod) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.getByCod(maquina_cod), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Maquina> create(@Valid @RequestBody MaquinaRequest maquinaRequest) throws ValidateFieldException {
        return new ResponseEntity<>(maquinaService.save(maquinaMapper.maquinaRequestToMaquina(maquinaRequest)), HttpStatus.CREATED);
    }


}
