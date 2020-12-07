package com.example.metalTest.almacen.repuesto.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoRequest {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String modelo;

    private String codigoProducto;

    @NotEmpty
    private String marca;

    private int precio;

    @NotNull
    private int existencia;

    @NotNull
    private String unidad;

    @NotNull
    private int puntoPedido;

    @NotNull
    private int stockObjetivo;

    @Min(1)
    @Max(3)
    private short tipoRepuesto;

    @NotEmpty
    private String ubicacion;

}
