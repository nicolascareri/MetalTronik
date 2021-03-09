package com.example.metalTest.usuarios.credenciales.usuarioCredencial.controller;

import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.personal.domain.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import  org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UsuarioCredencialController implements UserDetails {
    private String nombre_usuario;
    private String contrasenia;
    private Rol rol;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioCredencialController build(Personal personal){
        GrantedAuthority authority = new SimpleGrantedAuthority(personal.getCredencial().getRol().getRango().name());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new UsuarioCredencialController(personal.getCredencial().getNombre_usuario(), personal.getCredencial().getContrasenia(), personal.getCredencial().getRol(), authorities);
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
