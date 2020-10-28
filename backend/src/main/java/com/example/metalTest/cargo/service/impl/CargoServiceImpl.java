package com.example.metalTest.cargo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.controller.request.CargoRequest;
import com.example.metalTest.cargo.domain.Cargo;
import com.example.metalTest.cargo.mapper.CargoMapper;
import com.example.metalTest.cargo.repository.CargoRepository;
import com.example.metalTest.cargo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    CargoMapper cargoMapper;

    @Override
    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo getById(Integer id) throws ValidateFieldException {
        Optional<Cargo> optionalCargo = cargoRepository.findById(id);
        if (!optionalCargo.isPresent()){
            throw new ValidateFieldException("Cargo no encontrado", "id", String.valueOf(id));
        }

        return optionalCargo.get();
    }

    @Override
    public Cargo create(CargoRequest cargo){
        return cargoRepository.save(cargoMapper.cargoRequestToCargo(cargo));
    }

    @Override
    public Cargo update(CargoRequest cargo, Integer id) throws ValidateFieldException {
        Optional<Cargo> optionalCargo = cargoRepository.findById(id);
        if (!optionalCargo.isPresent()){
            throw new ValidateFieldException("Cargo no encontrado", "id", String.valueOf(id));
        }
        Cargo cargoActual = cargoMapper.cargoRequestToCargo(cargo);
        cargoActual.setId(id);
        return cargoRepository.save(cargoActual);
    }

    @Override
    public void remove(Integer id){ cargoRepository.deleteById(id); }


}
