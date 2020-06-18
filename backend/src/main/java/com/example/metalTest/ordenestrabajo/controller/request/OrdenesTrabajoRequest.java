package com.example.metalTest.ordenestrabajo.controller.request;
import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.planta.repository.PlantaRepository;
import com.example.metalTest.prioridades.repository.PrioridadesRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class OrdenesTrabajoRequest {

    @NotNull
    @ValidEntity(repository = PlantaRepository.class)
    private int planta_cod;
    @NotNull
    private String maquina_cod;
    @NotNull
    private String pedidoMateriales;
    @NotNull
    private String tarea;
    @NotNull
    @ValidEntity(repository = PrioridadesRepository.class)
    private int priodidad_cod;
    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private int tipo_cod;
    @NotNull
    private Date fechaRealizar;
    @NotNull
    private Date fechaEntrega;
    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo_cod;
    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int responsable_cod;
    @NotNull
    @Min(1)
    @Max(2)
    private short estado;
    @NotNull
    @ValidEntity(repository = SectorRepository.class)
    private int sector_cod;

    private String observaciones;

    @NotNull
    @Min(message = "No puede ser nulo", value = 1)
    private int ordenTerciarizacion;
}
