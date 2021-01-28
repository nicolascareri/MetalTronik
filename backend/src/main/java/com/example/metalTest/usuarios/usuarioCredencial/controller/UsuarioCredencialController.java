package com.example.metalTest.usuarios.usuarioCredencial.controller;

import com.example.metalTest.usuarios.credencial.domain.Credencial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import  org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UsuarioCredencialController implements UserDetails {
    private String nombre_usuario;
    private String contrasenia;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioCredencialController build(Credencial credencial){
        List<GrantedAuthority> authorities = credencial.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRango().name())).collect(Collectors.toList());
        return new UsuarioCredencialController(credencial.getNombre_usuario(), credencial.getContrasenia(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return nombre_usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
