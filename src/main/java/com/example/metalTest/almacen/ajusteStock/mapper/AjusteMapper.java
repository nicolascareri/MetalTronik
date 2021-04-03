package com.example.metalTest.almacen.ajusteStock.mapper;

import com.example.metalTest.almacen.ajusteStock.controller.request.AjusteRequest;
import com.example.metalTest.almacen.ajusteStock.controller.response.AjusteResponse;
import com.example.metalTest.almacen.ajusteStock.domain.AjusteStock;
import com.example.metalTest.almacen.ajusteStock.repository.AjusteRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AjusteRepository.class)
public interface AjusteMapper {

    AjusteStock ajusteRequestToAjusteStock(AjusteRequest ajusteRequest);
    AjusteResponse ajusteStockToAjusteResponse(AjusteStock ajusteStock);
}
