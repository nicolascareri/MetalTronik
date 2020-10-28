package com.example.metalTest.tareaTipo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tareaTipo.controller.request.TareaTipoRequest;
import com.example.metalTest.tareaTipo.domain.TareaTipo;

import java.util.List;

public interface TareaTipoService{

    List<TareaTipo> getAll();

    TareaTipo getById(Integer id) throws ValidateFieldException;

    TareaTipo create(TareaTipoRequest tareaTipoRequest) throws ValidateFieldException;

    TareaTipo update(TareaTipoRequest tareaTipoRequest, Integer id) throws ValidateFieldException;


}
