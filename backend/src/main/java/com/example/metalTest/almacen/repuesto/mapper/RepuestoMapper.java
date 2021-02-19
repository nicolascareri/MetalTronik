package com.example.metalTest.almacen.repuesto.mapper;

import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.request.StockRequest;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.domain.Stock;
import com.example.metalTest.parte.repository.ParteRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ParteRepository.class)
public interface RepuestoMapper {

    Repuesto repuestoRequestToRepuesto(RepuestoRequest repuestoRequest);

    Stock stockRequestToStock(StockRequest stockRequest);

}
