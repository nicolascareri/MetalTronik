package com.example.metalTest.ordenestrabajo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrdenesTrabajoService {

    List<OrdenesTrabajo> getAll();

    OrdenesTrabajo getById(Integer id) throws ValidateFieldException;

    OrdenesTrabajo create(OrdenesTrabajoRequest ordenesTrabajoRequest);

    OrdenesTrabajo update(OrdenesTrabajoRequest ordenesTrabajoRequest, Integer id) throws ValidateFieldException;
}
