package com.example.metalTest.parte.controller.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ParteRequest {
    @NotBlank
    private String codigo;
    @NotBlank
    private String nombre;
}
