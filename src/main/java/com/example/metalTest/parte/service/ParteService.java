package com.example.metalTest.parte.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.parte.controller.request.ParteRequest;
import com.example.metalTest.parte.controller.response.ParteResponse;

import java.util.List;

public interface ParteService {
    List<ParteResponse> getAll();

    ParteResponse create(ParteRequest parteRequest) throws ValidateFieldException;

    ParteResponse getById(Integer id) throws ValidateFieldException;

    List<ParteResponse> vincular(Integer id, List<Integer> parteList) throws ValidateFieldException;

    List<ParteResponse> findAllByMaquina(Integer id);

    void delete(Integer id) throws ValidateFieldException;
}
