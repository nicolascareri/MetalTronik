package com.example.metalTest.almacen.asociacion.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class AsociarList {
    @NotNull
    List<RepuestoAsociarRequest> requestList;
    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private Integer maquina_id;
    private Integer parte_id;
    @NotNull
    private String observaciones;
}
