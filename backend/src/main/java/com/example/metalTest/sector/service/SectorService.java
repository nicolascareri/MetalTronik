package com.example.metalTest.sector.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.sector.domain.Sector;

import java.util.List;

public interface SectorService {
    List<Sector> getAll();

    Sector getById(Integer id) throws ValidateFieldException;

    Sector create(Sector sector) throws ValidateFieldException;

    Sector update(Sector sectorRequestToSector, Integer id) throws ValidateFieldException;
}
