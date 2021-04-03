package com.example.metalTest.almacen.ajusteStock.controller.response;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AjusteResponse {
    private Repuesto repuesto;
    private Integer stock;
    private Integer stock_ajustado;
    private Date fecha_correccion;
}
