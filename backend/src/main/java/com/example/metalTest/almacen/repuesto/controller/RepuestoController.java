package com.example.metalTest.almacen.repuesto.controller;

import com.example.metalTest.almacen.repuesto.controller.request.AsociarList;
import com.example.metalTest.almacen.repuesto.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.repuesto.domain.Asociacion;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.service.RepuestoService;
import com.example.metalTest.maquina.domain.Maquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/repuesto")
public class RepuestoController {

    @Autowired
    RepuestoService repuestoService;

    @GetMapping
    public ResponseEntity<List<Repuesto>> getAll() {
        return new ResponseEntity<>(repuestoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepuestoResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Repuesto> create(@Valid @RequestBody RepuestoRequest repuestoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.create(repuestoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> update(@Valid @RequestBody RepuestoRequest repuestoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.update(repuestoRequest, id), HttpStatus.OK);
    }
    @GetMapping("/vinculados")
    public ResponseEntity<List<Asociacion>> getVinculados(){
        return new ResponseEntity<>(repuestoService.getVinculados(), HttpStatus.OK);
    }

    @PutMapping("/vincular/maquina-parte")
    public Repuesto asociar(@Valid @RequestBody AsociarList asociarList){
        repuestoService.asociar(asociarList);
        return null;
    }
    @GetMapping("/sin-vincular")
    public ResponseEntity<List<Maquina>> getSinVincular(){
        return new ResponseEntity<>(repuestoService.getSinVincular(), HttpStatus.OK);
    }

}