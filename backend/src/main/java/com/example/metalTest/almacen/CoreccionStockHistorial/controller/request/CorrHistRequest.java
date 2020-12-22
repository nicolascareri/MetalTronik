package com.example.metalTest.almacen.CoreccionStockHistorial.controller.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CorrHistRequest {


    private String nombre;

    //Codigo de barras
    private String codigoProducto;

    private String modelo;

    private Integer stock;

    private Date fecha_correccion;
}
