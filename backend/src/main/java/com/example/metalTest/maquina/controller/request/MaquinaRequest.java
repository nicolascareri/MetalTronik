package com.example.metalTest.maquina.controller.request;

import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tipo.repository.TipoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MaquinaRequest {

    @NotBlank
    private String maquina_cod;

    @NotNull
    private Estado estado;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer planta_id;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer sector_id;

    @NotBlank
    private String equipo;

    @NotBlank
    private String descripcion;

}
