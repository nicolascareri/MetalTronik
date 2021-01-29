package com.example.metalTest.usuarios.personal.controller.response;

import com.example.metalTest.security.jwt.JwtDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private JwtDto jwtDto;

}
