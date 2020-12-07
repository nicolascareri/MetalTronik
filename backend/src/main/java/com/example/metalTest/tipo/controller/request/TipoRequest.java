package com.example.metalTest.tipo.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class TipoRequest {

    @NotNull
    private String nombre;
    @NotNull
    private String tipo;

}
