package com.example.metalTest.ordenestrabajo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.service.OrdenesTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/ordenes-trabajo")
public class OrdenesTrabajoController {

    @Autowired
    OrdenesTrabajoService ordenesTrabajoService;


    @GetMapping
    public ResponseEntity<List<OrdenesTrabajoResponse>> getAll() {
        return new ResponseEntity<>(ordenesTrabajoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenesTrabajoResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(ordenesTrabajoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdenesTrabajoResponse> create(@Valid @RequestBody OrdenesTrabajoRequest ordenesTrabajoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(ordenesTrabajoService.create(ordenesTrabajoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenesTrabajoResponse> update(@Valid @RequestBody OrdenesTrabajoRequest ordenesTrabajoRequest,
                                                 @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(ordenesTrabajoService.update(ordenesTrabajoRequest, id), HttpStatus.OK);
    }


}
