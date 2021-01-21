package com.example.metalTest.usuarios.usuario.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String nombre_usuario;
    private String password;
}
