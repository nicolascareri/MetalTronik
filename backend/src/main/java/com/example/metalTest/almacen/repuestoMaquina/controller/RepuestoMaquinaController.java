package com.example.metalTest.almacen.repuestoMaquina.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuestoMaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.almacen.repuestoMaquina.service.RepuestoMaquinaService;
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

    @PutMapping("/maquina/{id}/vincular")
    public ResponseEntity<List<RepuestoMaquinaResponse>> vincular(@Valid @RequestBody List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoMaquinaService.vincular(repuestoMaquinaRequestList, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RepuestoMaquinaResponse>> getAll(){
        return new ResponseEntity<>(repuestoMaquinaService.getAll(), HttpStatus.OK);
    }

}
