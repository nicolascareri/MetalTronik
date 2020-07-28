package com.example.metalTest.repuesto.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.repository.RepuestoRepository;
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
    private int cantidadInstalada;

}
