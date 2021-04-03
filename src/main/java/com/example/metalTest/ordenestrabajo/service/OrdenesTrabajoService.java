package com.example.metalTest.ordenestrabajo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;

import java.util.List;

public interface OrdenesTrabajoService {

    List<OrdenesTrabajoResponse> getAll();

    OrdenesTrabajoResponse getById(Integer id) throws ValidateFieldException;

    OrdenesTrabajoResponse create(OrdenesTrabajoRequest ordenesTrabajoRequest) throws ValidateFieldException;

    OrdenesTrabajoResponse update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException;
}
