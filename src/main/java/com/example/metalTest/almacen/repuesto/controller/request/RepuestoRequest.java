package com.example.metalTest.almacen.repuesto.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tipo.repository.TipoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoRequest {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String modelo;

    @NotNull
    private String codigo_producto;

    @NotEmpty
    private String marca;

    @NotNull
    private StockRequest stock;

    @NotNull
    private String unidad;


    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer tipoRepuesto_id;

    @NotEmpty //en que estanteria
    private String ubicacion;

}
