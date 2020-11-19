package com.example.metalTest.indicadores.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndicatorResponse {
    // { data: [65], label: 'Usuario 1' },
    List<Integer> data;
    String label;
}
