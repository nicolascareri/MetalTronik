package com.example.metalTest.repuestomaquina.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoMaquinaRequest {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String modelo;

    @NotNull
    private int cantidadInstalada;

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina_cod;

}
