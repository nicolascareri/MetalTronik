package com.example.metalTest.usuarios.usuario.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioRequest extends PersonalRequest {

    @NotBlank
    private String nombre_usuario;
    @NotBlank
    private String contrasenia;

    @NotNull
    @ValidEntity(repository = RolRepository.class)
    private Integer rol;
}
