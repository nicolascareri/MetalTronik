package com.example.metalTest.almacen.asociacion.controller.request;

import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.common.validator.ValidEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class RepuestoAsociarRequest {
    @NotNull
    private Integer cantidad_instalada;
    @NotNull
    @ValidEntity(repository = RepuestoRepository.class)
    private Integer repuesto_id;
}
