package com.example.metalTest.almacen.repuesto.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepuestoReducidoResponse {
    private int id;
    private String nombre;
    private String modelo;
    private String marca;
    private String codigoProducto;
    private String existencia;
    private String precio;
    private String unidad;
    private int puntoPedido;
    private int stockObjetivo;
    private short tipoRepuesto;
    private String ubicacion;
}
