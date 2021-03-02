package com.example.metalTest.usuarios.usuario.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.security.jwt.JwtDto;
import com.example.metalTest.usuarios.personal.controller.request.LoginRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal puestos" +bindingResult.toString(), HttpStatus.BAD_REQUEST);
        JwtDto jwtDto = usuarioService.login(loginRequest);
        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }
    @PostMapping("/create/{id}")
    public ResponseEntity<Personal> create(@Valid @RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<Personal>(usuarioService.create(usuarioRequest, id), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Personal>> getAll() {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(usuarioService.getById(id), HttpStatus.OK);
    }
}
