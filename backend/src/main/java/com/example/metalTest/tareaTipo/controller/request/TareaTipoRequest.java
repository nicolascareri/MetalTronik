package com.example.metalTest.tareaTipo.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TareaTipoRequest {
    @NotNull
    private int id;
    @NotNull
    private String nombre;

}
