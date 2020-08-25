package com.example.metalTest.movimiento.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.movimiento.domain.Movimiento;
import com.example.metalTest.movimiento.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAll(){
        return new ResponseEntity<>(movimientoService.getAll(), HttpStatus.OK);
    }

//    @GetMapping("/tipo/{tipo}")
//    public ResponseEntity<List<Movimiento>> getByTipo(@PathVariable Short tipo) throws ValidateFieldException {
//        return new ResponseEntity<>(movimientoService.getByTipo(tipo), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Movimiento> create(@Valid @RequestBody MovimientoRequest movimientoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(movimientoService.create(movimientoRequest), HttpStatus.CREATED);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Movimiento> update(@Valid @RequestBody MovimientoRequest movimientoRequest, @PathVariable Integer id){
        return new ResponseEntity<>(movimientoService.update(movimientoRequest, id), HttpStatus.OK);
    }*/

}
