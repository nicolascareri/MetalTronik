package com.example.metalTest.almacen.salida.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SalidaRequest {
    @ValidEntity(repository = RepuestoRepository.class)
    private int repuesto_cod;
    @NotNull
    private Date fecha;
    @ValidEntity(repository = UsuarioRepository.class)
    private Integer solicitante_cod;
    @ValidEntity(repository = SectorRepository.class)
    private Integer sector_cod;
    @NotNull
    private Integer cantidad;
}
