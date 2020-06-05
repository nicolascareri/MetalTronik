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

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
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
    @GetMapping("/{id}")
    public ResponseEntity<OrdenesTrabajo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(ordenesTrabajoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdenesTrabajo> create(@Valid @RequestBody OrdenesTrabajoRequest ordenesTrabajoRequest){
        return new ResponseEntity<>(ordenesTrabajoService.create(ordenesTrabajoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenesTrabajo> update(@Valid @RequestBody OrdenesTrabajoRequest ordenesTrabajoRequest,
                                                 @PathVariable Integer id) throws ValidateFieldException{
        return new ResponseEntity<>(ordenesTrabajoService.update(ordenesTrabajoRequest, id), HttpStatus.OK);
    }


}
