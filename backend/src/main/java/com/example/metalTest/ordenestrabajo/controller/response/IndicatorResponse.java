package com.example.metalTest.ordenestrabajo.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndicatorResponse {
// { data: [65, 59, 80, 81, 56, 55, 40], label: 'Usuario 1' }
    List<Integer> data;
    String label;
}
