package com.example.metalTest.indicadores.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Torta {
    private List<String> labels;
    private List<Integer> number;
}
