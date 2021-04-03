package com.example.metalTest.almacen.repuesto.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequest {

    private Integer minimo;
    private Integer objetivo;
    private Integer actual;
}
