package com.example.metalTest.producto.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.repuestomaquina.repository.RepuestoMaquinaRepository;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductoRequest {

    @NotNull
    @ValidEntity(repository = RepuestoMaquinaRepository.class)
    private int repuestoMaquina_cod;

    @NotNull
    private int precio;

    @NotNull
    private int existencia;

    @NotBlank
    private String unidad;

    @NotNull
    private int puntoPedido;

    @NotNull
    private int stockObjetivo;

    @NotNull
    private short tipoRepuesto;

    @NotBlank
    private String ubicacion;

}
