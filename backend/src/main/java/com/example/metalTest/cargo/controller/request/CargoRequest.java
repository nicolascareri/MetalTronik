package com.example.metalTest.cargo.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CargoRequest {
    @NotNull
    private String nombre_cargo;
}
