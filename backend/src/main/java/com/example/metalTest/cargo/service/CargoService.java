package com.example.metalTest.cargo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.domain.Cargo;

import java.util.List;

public interface CargoService {

    List<Cargo> getAll();

    Cargo getById(Integer id) throws ValidateFieldException;

    Cargo create(Cargo cargo) throws ValidateFieldException;

    Cargo update(Cargo cargo, Integer id) throws ValidateFieldException;

    void remove(Integer id);
}
