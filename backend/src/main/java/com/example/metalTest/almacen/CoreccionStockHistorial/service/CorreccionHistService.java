package com.example.metalTest.almacen.CoreccionStockHistorial.service;

import com.example.metalTest.almacen.CoreccionStockHistorial.controller.request.CorrHistRequest;
import com.example.metalTest.almacen.CoreccionStockHistorial.controller.response.CorrHistResponse;
import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;

import java.util.List;

public interface CorreccionHistService {
    List<CorreccionHistorial> getAll();

    CorrHistResponse create(CorrHistRequest correccionHistorial, Integer repuesto_id);
}
