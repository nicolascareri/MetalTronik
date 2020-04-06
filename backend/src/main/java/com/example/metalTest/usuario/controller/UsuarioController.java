package com.example.metalTest.usuario.controller;

import com.example.metalTest.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.mapper.UsuarioMapper;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.apache.coyote.Response;
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
    private UsuarioRepository usuarioRepository;

    /*@Autowired
    private UsuarioMapper usuarioMapper;*/

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }
    /*@PostMapping
    public ResponseEntity createUsuario(@RequestBody UsuarioRequest usuarioRequest){
        Usuario u = usuarioRepository.save(UsuarioMapper.usuarioRequestToUsuario(usuarioRequest));
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }*/
}
