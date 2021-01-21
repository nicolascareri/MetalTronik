package com.example.metalTest.usuarios.usuarioCredencial.service;

import com.example.metalTest.usuarios.usuario.domain.Usuario;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import com.example.metalTest.usuarios.usuarioCredencial.controller.UsuarioCredencialController;
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
        Usuario usuario = usuarioService.findFyNombreUsuario(user_name);
        return UsuarioCredencialController.build(usuario.getCredencial());
    }
}
