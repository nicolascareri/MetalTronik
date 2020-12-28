package com.example.metalTest.almacen.movimiento.entrada.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class EntradaRequest {
    @NotNull
    private Integer precio_unitario;
    @NotNull
    private Integer precio_total;
    @NotEmpty
    private String numeroOrdenCompra;
    @NotEmpty
    private String proveedor;
    @ValidEntity(repository = RepuestoRepository.class)
    private Integer repuesto_id;
    @NotNull
    private Integer cantiad;
    @NotNull
    private Date fecha;
}
