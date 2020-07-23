package com.example.metalTest.repuesto.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepuestoMaquinaRequest {

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina;

    @NotNull
    private int cantidadInstalada;

}
