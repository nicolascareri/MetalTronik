package com.example.metalTest.parte.controller.response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParteParaOrdenResponse {
    private Integer id;
    private String nombre;
    private String codigo;
}
