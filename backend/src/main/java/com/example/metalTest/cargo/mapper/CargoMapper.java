package com.example.metalTest.cargo.mapper;

import com.example.metalTest.cargo.controller.request.CargoRequest;
import com.example.metalTest.cargo.domain.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    Cargo cargoRequestToCargo(CargoRequest cargo);
}
