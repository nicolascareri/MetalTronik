package com.example.metalTest.movimiento.repository;

import com.example.metalTest.movimiento.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    @Query("SELECT m FROM Movimiento m WHERE m.tipoMovimiento = :tipo")
    List<Movimiento> findByTipo(@Param("tipo") Short tipo);
}
