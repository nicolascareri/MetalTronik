package com.example.metalTest.preventivo.registro.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.preventivo.registro.controller.request.RegistroRequest;
import com.example.metalTest.preventivo.registro.controller.response.RegistroResponse;
import com.example.metalTest.preventivo.registro.controller.response.RegistroResponseParaListado;
import com.example.metalTest.preventivo.registro.mapper.RegistroMapper;
import com.example.metalTest.preventivo.registro.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @Autowired
    RegistroMapper registroMapper;

    @GetMapping("/planificar/{date}")
    public ResponseEntity<List<RegistroResponse>> getForMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(registroMapper.toRegistroResponseList(registroService.getForMonth(date)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<List<RegistroResponseParaListado>> savePlanificacionActual() throws ValidateFieldException {
        return new ResponseEntity<>(registroMapper.toRegistroResponseParaListadoList(registroService.savePlanificacionActual()),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseParaListado> getById(@PathVariable Integer id) throws ValidateFieldException{
        return new ResponseEntity<>(registroMapper.toRegistroResponseParaListado(registroService.getById(id)),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistroResponseParaListado>update(@RequestBody @Valid RegistroRequest registroRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(registroMapper.toRegistroResponseParaListado(registroService.update(registroRequest, id)), HttpStatus.OK);
    }
    @GetMapping("/actual/{date}")
    public ResponseEntity<List<RegistroResponseParaListado>> getActualOrPast(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(registroMapper.toRegistroResponseParaListadoList(registroService.getActualOrPast(date)), HttpStatus.OK);
    }
}
