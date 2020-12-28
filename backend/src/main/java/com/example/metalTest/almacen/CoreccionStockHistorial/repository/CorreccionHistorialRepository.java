package com.example.metalTest.almacen.CoreccionStockHistorial.repository;

import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorreccionHistorialRepository extends JpaRepository<CorreccionHistorial, Integer> {
}
