package com.example.metalTest.almacen.CoreccionStockHistorial.mapper;

import com.example.metalTest.almacen.CoreccionStockHistorial.controller.request.CorrHistRequest;
import com.example.metalTest.almacen.CoreccionStockHistorial.controller.response.CorrHistResponse;
import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorreccionMapper {

    CorreccionHistorial corrHistRequestToCorreccionHistorial(CorrHistRequest corrHistRequest);
    CorrHistResponse correccionHistorialToCorrHistResponse(CorreccionHistorial correccionHistorial);
}
