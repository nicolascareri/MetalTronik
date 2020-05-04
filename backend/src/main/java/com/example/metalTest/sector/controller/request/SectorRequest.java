package com.example.metalTest.sector.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectorRequest {

    @NotBlank
    private String descripcion;
    @NotNull
    private short estado;
}
