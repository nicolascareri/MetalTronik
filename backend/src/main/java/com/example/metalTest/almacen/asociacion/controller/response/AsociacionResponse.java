package com.example.metalTest.almacen.asociacion.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsociacionResponse {
    private Integer cantidad_instalada;
    private Integer repuesto_id;
    private Integer maquina_id;
    private Integer parte_id;
    private String observaciones;
}
