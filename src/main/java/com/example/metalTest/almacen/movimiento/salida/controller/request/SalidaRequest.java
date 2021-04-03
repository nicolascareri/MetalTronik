package com.example.metalTest.almacen.movimiento.salida.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SalidaRequest {
    @ValidEntity(repository = RepuestoRepository.class)
    private Integer repuesto_id;
    @NotNull
    private Date fecha;
    @ValidEntity(repository = PersonalRepository.class)
    private Integer solicitante_id;
    @ValidEntity(repository = TipoRepository.class)
    private Integer sector_id;
    @NotNull
    private Integer cantidad;
}
