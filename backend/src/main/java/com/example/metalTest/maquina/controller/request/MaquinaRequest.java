package com.example.metalTest.maquina.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MaquinaRequest {

    @NotBlank
    private String maquina_cod;

    @NotNull
    private short estado;

}
