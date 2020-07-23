package com.example.metalTest.sector.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.sector.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository sectorRepository;

    @Override
    public List<Sector> getAll() {
        return sectorRepository.getAllSector();
    }

    @Override
    public Sector getById(Integer id) throws ValidateFieldException {
        Optional<Sector> opt = sectorRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El sector que al que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }

    @Override
    public Sector create(Sector sector) throws ValidateFieldException {

        if (sector.getEstado() != Estado.ACTIVO.getValue() && sector.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(sector.getEstado()));
        }

        if (sectorRepository.checkDescripcionExistance(sector.getDescripcion(), sector.getId()) != 0) {
            throw new ValidateFieldException("Valor en campo invalido", "descripcion",
                    String.valueOf(sector.getDescripcion()));
        }

        Date date = new Date(System.currentTimeMillis());
        sector.setFechaAlta(date);
        return sectorRepository.save(sector);
    }

    @Override
    public Sector update(Sector sector, Integer id) throws ValidateFieldException {
        Optional<Sector> opt = sectorRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El sector que desea acceder no existe", "id", String.valueOf(id));
        }
        if (sector.getEstado() != Estado.ACTIVO.getValue() && sector.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(sector.getEstado()));
        }

        Sector update = opt.get();
        sector.setId(id);
        Date date = new Date(System.currentTimeMillis());
        sector.setFechaAlta(date);
        if (sectorRepository.checkDescripcionExistance(sector.getDescripcion(), sector.getId()) != 0) {
            throw new ValidateFieldException("Valor en campo invalido", "descripcion",
                    String.valueOf(sector.getDescripcion()));
        }
        return sectorRepository.save(sector);
    }
}
