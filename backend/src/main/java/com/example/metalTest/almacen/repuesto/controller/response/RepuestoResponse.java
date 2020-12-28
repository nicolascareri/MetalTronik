package com.example.metalTest.almacen.repuesto.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepuestoResponse {
    private int id;
    private String nombre;
    private String modelo;
    private String marca;
    private String codigoProducto;
    private String existencia;
    private Integer precio_unitario;
    private Integer precio_total;
}
