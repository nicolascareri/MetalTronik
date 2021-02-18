package com.example.metalTest.usuarios.credenciales.usuarioCredencial.service;

import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.credenciales.usuarioCredencial.controller.UsuarioCredencialController;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDitailService implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        Personal personal = usuarioService.findFyNombreUsuario(user_name);
        return UsuarioCredencialController.build(personal);
    }
}

