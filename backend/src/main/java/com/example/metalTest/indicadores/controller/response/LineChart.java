package com.example.metalTest.indicadores.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LineChart {
    String maquina_cod;
    List<Integer> data;
    List<String> label;
}
