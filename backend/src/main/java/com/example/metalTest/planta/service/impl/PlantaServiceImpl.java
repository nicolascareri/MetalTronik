package com.example.metalTest.planta.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.planta.mapper.PlantaMapper;
import com.example.metalTest.planta.repository.PlantaRepository;
import com.example.metalTest.planta.service.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaServiceImpl implements PlantaService {
    @Autowired
    PlantaRepository plantaRepository;

    @Autowired
    PlantaMapper plantaMapper;

    @Override
    public List<Planta> getAll() {
        return plantaRepository.findAll();
    }

    @Override
    public Planta getById(Integer id) throws ValidateFieldException {
        Optional<Planta> opt = plantaRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La planta que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }

    @Override
    public Planta create(Planta planta) throws ValidateFieldException {
        if (planta.getEstado() != Estado.ACTIVO.getValue() && planta.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(planta.getEstado()));
        }
        if (plantaRepository.checkNombreExistance(planta.getNombre(), planta.getId()) != 0) {
            throw new ValidateFieldException("Valor en campo invalido", "nombre",
                    String.valueOf(planta.getNombre()));
        }
        return plantaRepository.save(planta);
    }

    @Override
    public Planta update(Planta planta, Integer id) throws ValidateFieldException {
        Optional<Planta> opt = plantaRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("La planta que desea acceder no existe", "id", String.valueOf(id));
        }
        if (planta.getEstado() != Estado.ACTIVO.getValue() && planta.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(planta.getEstado()));
        }
        if (plantaRepository.checkNombreExistance(planta.getNombre(), planta.getId()) != 0) {
            throw new ValidateFieldException("Valor en campo invalido", "nombre",
                    String.valueOf(planta.getNombre()));
        }
        Planta update = opt.get();
        update.setNombre(planta.getNombre());
        update.setEstado(planta.getEstado());
        return plantaRepository.save(update);
    }
}
