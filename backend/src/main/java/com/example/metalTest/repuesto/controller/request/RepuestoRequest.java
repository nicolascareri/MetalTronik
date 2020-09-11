package com.example.metalTest.repuesto.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoRequest {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String modelo;

    private String codigoProducto;

    @NotEmpty
    private String marca;

    private int precio;

    @NotNull
    private int existencia;

    @NotNull
    private String unidad;

    @NotNull
    private int puntoPedido;

    @NotNull
    private int stockObjetivo;

    @Min(1)
    @Max(3)
    private short tipoRepuesto;

    @NotEmpty
    private String ubicacion;

}
