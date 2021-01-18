package com.example.metalTest.usuarios.rol.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.rol.domain.Rol;
import com.example.metalTest.usuarios.rol.service.RolService;
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
    @GetMapping()
    public ResponseEntity<List<Rol>> getByTipo(@PathVariable String tipo){
       // return new ResponseEntity<>(rolService, HttpStatus.OK);
        return null;
    }
    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody RolRequest rolRequest) throws ValidateFieldException {
        return new ResponseEntity<>(rolService.create(rolRequest), HttpStatus.CREATED);
    }
}
