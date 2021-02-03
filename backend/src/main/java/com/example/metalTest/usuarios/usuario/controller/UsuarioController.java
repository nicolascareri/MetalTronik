package com.example.metalTest.usuarios.usuario.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.security.jwt.JwtDto;
import com.example.metalTest.security.jwt.JwtProvider;
import com.example.metalTest.usuarios.personal.controller.request.LoginRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal puestos" +bindingResult.toString(), HttpStatus.BAD_REQUEST);
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getNombre_usuario(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }
    @PostMapping("/create/{id}")
    public ResponseEntity<Personal> create(@Valid @RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<Personal>(usuarioService.create(usuarioRequest, id), HttpStatus.CREATED);
    }
}
