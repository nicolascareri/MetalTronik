package com.example.metalTest.almacen.repuesto.controller.response;

import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String precio;
    private List<RepuestoMaquinaResponse> repuestoMaquinaList;
}
