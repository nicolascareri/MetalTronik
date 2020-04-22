package com.example.metalTest.maquina.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MaquinaRequest {
    @NotBlank
    private String maquina_cod;
    @NotBlank
    private String sector;
}
