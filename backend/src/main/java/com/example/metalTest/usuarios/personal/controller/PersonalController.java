package com.example.metalTest.usuarios.personal.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.security.jwt.JwtDto;
import com.example.metalTest.security.jwt.JwtProvider;
import com.example.metalTest.usuarios.personal.controller.request.LoginRequest;
import com.example.metalTest.usuarios.personal.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.service.PersonalService;
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
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class PersonalController {

    @Autowired
    PersonalService personalService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<List<Personal>> getAll() {
        return new ResponseEntity<>(personalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(personalService.getById(id), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Personal> updateUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(personalService.update(id, usuarioRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest, BindingResult bindingResult) throws ValidateFieldException {

        return new ResponseEntity<>(personalService.create(usuarioRequest), HttpStatus.CREATED);
    }

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
}
