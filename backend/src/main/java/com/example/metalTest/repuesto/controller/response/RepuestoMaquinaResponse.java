package com.example.metalTest.repuesto.controller.response;

import com.example.metalTest.maquina.domain.Maquina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepuestoMaquinaResponse {

    private String nombre;
    private int cantidadInstalada;
    private Maquina maquina;

}
