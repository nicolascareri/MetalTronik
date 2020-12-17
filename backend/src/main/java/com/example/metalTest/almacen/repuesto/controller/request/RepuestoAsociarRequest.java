package com.example.metalTest.almacen.repuesto.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class RepuestoAsociarRequest {
    @NotNull
    private Integer cantidad_instalada;
    @NotNull
    private Integer repuesto_id;
}
