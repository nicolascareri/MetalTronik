package com.example.metalTest.security.jwt;

import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
public class JwtDto {

    private String token;

    private String bearer = "Bearer";

    private String nombre_usuario;

    private Rol rol;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nombre_usuario, Rol rol, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombre_usuario = nombre_usuario;
        this.authorities = authorities;
        this.rol = rol;
    }
}
