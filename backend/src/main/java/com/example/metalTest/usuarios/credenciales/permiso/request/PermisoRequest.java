package com.example.metalTest.usuarios.credenciales.permiso.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tipo.repository.TipoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class PermisoRequest {

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer sector;
    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer permiso;
}
