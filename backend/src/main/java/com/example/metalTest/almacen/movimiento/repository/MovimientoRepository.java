package com.example.metalTest.almacen.movimiento.repository;

import com.example.metalTest.almacen.movimiento.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
//    @Query("SELECT m FROM Movimiento m WHERE m.tipoMovimiento = :tipo")
//    List<Movimiento> findByTipo(@Param("tipo") Short tipo);
}
