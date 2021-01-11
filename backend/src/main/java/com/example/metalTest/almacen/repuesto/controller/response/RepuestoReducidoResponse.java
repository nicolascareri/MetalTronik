package com.example.metalTest.almacen.repuesto.controller.response;
import com.example.metalTest.tipo.domain.Tipo;
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
    private Integer precio_unitario;
    private Integer precio_total;
    private String unidad;
    private int puntoPedido;
    private int stockObjetivo;
    private Tipo tipo_repuesto;
    private String ubicacion;
}
