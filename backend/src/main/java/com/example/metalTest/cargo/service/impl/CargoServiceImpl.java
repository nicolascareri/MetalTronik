package com.example.metalTest.cargo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.domain.Cargo;
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

    @Override
    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo getById(Integer id) throws ValidateFieldException {return cargoRepository.findById(id).get(); }

    @Override
    public Cargo create(Cargo cargo) throws ValidateFieldException {
        return cargoRepository.save(cargo);
    }

    @Override
    public Cargo update(Cargo cargo, Integer id) throws ValidateFieldException {
        Optional<Cargo> optionalCargo = cargoRepository.findById(id);
        if (!optionalCargo.isPresent()){
            throw new ValidateFieldException("Cargo no encontrado", "", "");
        }
        cargo.setId(id);
        return cargoRepository.save(cargo);
    }

    @Override
    public void remove(Integer id){ cargoRepository.deleteById(id); }


}
