package com.example.metalTest.usuario.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.mapper.UsuarioMapper;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id ) throws ValidateFieldException {
        return new ResponseEntity(usuarioRepository.findById(id), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id ) throws ValidateFieldException {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setApellido(usuarioRequest.getApellido());
        usuario.setDni(usuarioRequest.getDni());
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioRequest usuarioRequest) throws ValidateFieldException {
        Usuario usuario = usuarioMapper.usuarioRequestToUsuario(usuarioRequest);
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }
}
