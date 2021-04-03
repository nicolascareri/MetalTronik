package com.example.metalTest.almacen.ajusteStock.controller.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AjusteRequest {


    @NotNull
    private String nombre;

    //Codigo de barras
    @NotNull
    private String codigo_producto;

    @NotNull
    private String modelo;

    @NotNull
    private String marca;

    @NotNull
    private Integer stock;
    @NotNull
    private Integer stock_ajustado;

    @NotNull
    private Date fecha_correccion;
}
