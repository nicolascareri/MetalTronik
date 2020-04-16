package com.example.metalTest.ordenestrabajo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.mapper.OrdenesTrabajoMapper;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenesTrabajoController {

    @Autowired
    OrdenesTrabajoMapper ordenesTrabajoMapper;

    @Autowired
    OrdenesTrabajoService ordenesTrabajoService;

    @GetMapping
    public ResponseEntity<List<OrdenesTrabajo>> getAll(){
        return new ResponseEntity<>(ordenesTrabajoService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/api/{id}")
    public ResponseEntity<OrdenesTrabajo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(ordenesTrabajoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdenesTrabajo> create(@RequestBody OrdenesTrabajoRequest ordenesTrabajoRequest){
        return new ResponseEntity<>(ordenesTrabajoService.create(ordenesTrabajoRequest), HttpStatus.CREATED);
    }

}
