package com.example.metalTest.usuarios.personal.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String nombre_usuario;
    private String password;
}
