package com.example.metalTest.cargo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.controller.request.CargoRequest;
import com.example.metalTest.cargo.domain.Cargo;

import java.util.List;

public interface CargoService {

    List<Cargo> getAll();

    Cargo getById(Integer id) throws ValidateFieldException;

    Cargo create(CargoRequest cargo) throws ValidateFieldException;

    Cargo update(CargoRequest cargo, Integer id) throws ValidateFieldException;

    void remove(Integer id);
}
