package com.example.metalTest.almacen.asociacion.controller.request;

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
    private Integer maquina_id;
    private Integer parte_id;
}
