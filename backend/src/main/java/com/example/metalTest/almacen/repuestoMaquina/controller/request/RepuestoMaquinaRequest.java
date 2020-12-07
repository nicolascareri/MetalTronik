package com.example.metalTest.almacen.repuestoMaquina.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoMaquinaRequest {

    @NotNull
    @ValidEntity(repository = RepuestoRepository.class)
    private int repuesto_cod;

    @NotNull
    private Integer cantidad_instalada;
}
