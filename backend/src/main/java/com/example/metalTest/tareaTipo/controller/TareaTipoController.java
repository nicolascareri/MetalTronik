package com.example.metalTest.tareaTipo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tareaTipo.controller.request.TareaTipoRequest;
import com.example.metalTest.tareaTipo.domain.TareaTipo;
import com.example.metalTest.tareaTipo.service.TareaTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tareatipo")
public class TareaTipoController {

    @Autowired
    TareaTipoService tareaTipoService;

    @GetMapping
    public ResponseEntity<List<TareaTipo>> getAll() {
        return new ResponseEntity<>(tareaTipoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaTipo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tareaTipoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareaTipo> create(@Valid @RequestBody TareaTipoRequest tareaTipoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(tareaTipoService.create(tareaTipoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaTipo> update(@Valid @RequestBody TareaTipoRequest tareaTipoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(tareaTipoService.update(tareaTipoRequest, id), HttpStatus.OK);
    }

}
