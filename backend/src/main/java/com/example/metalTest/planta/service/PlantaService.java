package com.example.metalTest.planta.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.planta.domain.Planta;

import java.util.List;

public interface PlantaService {
    List<Planta> getAll();

    Planta getById(Integer id) throws ValidateFieldException;

    Planta create(Planta planta) throws ValidateFieldException;

    Planta update(Planta planta, Integer id) throws ValidateFieldException;
}
