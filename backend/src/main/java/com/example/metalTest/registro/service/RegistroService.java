package com.example.metalTest.registro.service;

import com.example.metalTest.registro.controller.response.RegistroResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface RegistroService {
    List<RegistroResponse> getForMonth(Date date);
}
