package com.example.metalTest.usuarios.usuario.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.rol.repository.RolRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String nombre_usuario;
    @NotBlank
    private String contrasenia;
    @NotNull
    private int legajo;

    @NotNull
    private Date fnacimiento;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer cargo;

    @NotNull
    @ValidEntity(repository = RolRepository.class)
    private Integer rol;

    @NotBlank
    private String correo_electronico;

    @NotBlank
    private String pais;

    @NotBlank
    private String provincia;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String direccion;

}
