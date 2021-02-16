package com.example.metalTest.usuarios.credenciales.rol.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.credenciales.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    RolService rolService;
    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody RolRequest rolRequest) throws ValidateFieldException {
        return new ResponseEntity<>(rolService.create(rolRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getAll() {
        return new ResponseEntity<>(rolService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(rolService.getById(id), HttpStatus.OK);

    }
}
