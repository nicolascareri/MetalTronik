package com.example.metalTest.maquina.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.planta.repository.PlantaRepository;
import com.example.metalTest.sector.repository.SectorRepository;
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
    private short estado;

    @NotNull
    @ValidEntity(repository = PlantaRepository.class)
    private int planta_cod;

    @NotNull
    @ValidEntity(repository = SectorRepository.class)
    private int sector_cod;

    @NotBlank
    private String equipo;

    @NotBlank
    private String descripcion;

}
