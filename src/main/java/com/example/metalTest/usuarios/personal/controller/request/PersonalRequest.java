package com.example.metalTest.usuarios.personal.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.domain.Direccion;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PersonalRequest {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    private int legajo;

    @NotNull
    private Date fnacimiento;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer cargo;

    @NotBlank
    private String correo_electronico;

    @NotNull
    private Direccion direccion;

}
