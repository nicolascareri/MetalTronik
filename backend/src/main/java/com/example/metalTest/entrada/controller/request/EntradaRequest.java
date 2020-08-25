package com.example.metalTest.entrada.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class EntradaRequest {
    @NotNull
    private Integer precio;
    @NotEmpty
    private String numeroOrdenCompra;
    @NotEmpty
    private String proveedor;
    @ValidEntity(repository = RepuestoRepository.class)
    private Integer repuesto_cod;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Date fecha;
}
