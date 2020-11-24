package com.example.metalTest.usuario.controller.request;

import com.example.metalTest.cargo.repository.CargoRepository;
import com.example.metalTest.common.validator.ValidEntity;
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
    @NotNull
    private int dni;
    @NotNull
    private short estado;
    @NotBlank
    private String nombre_usuario;
    @NotBlank
    private String contrasenia;
    @NotNull
    private int legajo;

    @NotNull
    private Date fnacimiento;

    @ValidEntity(repository =  CargoRepository.class)
    private Integer cargo_id;

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
