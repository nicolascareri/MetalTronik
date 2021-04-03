package com.example.metalTest.parte.controller.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParteResponse {
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer maquinaId;
}
