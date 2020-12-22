package com.example.metalTest.almacen.CoreccionStockHistorial.controller.response;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CorrHistResponse {
    private Repuesto repuesto;

    private String nombre;

    //Codigo de barras
    private String codigoProducto;

    private String modelo;

    private Integer stock;

    private Date fecha_correccion;
}
