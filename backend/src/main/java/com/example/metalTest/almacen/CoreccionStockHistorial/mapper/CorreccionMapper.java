package com.example.metalTest.almacen.CoreccionStockHistorial.mapper;

import com.example.metalTest.almacen.CoreccionStockHistorial.controller.request.CorrHistRequest;
import com.example.metalTest.almacen.CoreccionStockHistorial.controller.response.CorrHistResponse;
import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;
import com.example.metalTest.almacen.CoreccionStockHistorial.repository.CorreccionHistorialRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CorreccionHistorialRepository.class)
public interface CorreccionMapper {

    CorreccionHistorial corrHistRequestToCorreccionHistorial(CorrHistRequest corrHistRequest);
    CorrHistResponse correccionHistorialToCorrHistResponse(CorreccionHistorial correccionHistorial);
}
