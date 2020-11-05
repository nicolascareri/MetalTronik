package com.example.metalTest.ordenestrabajo.repository;

import com.example.metalTest.ordenestrabajo.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenesTrabajoRepository extends JpaRepository<OrdenesTrabajo, Integer> {

    @Query(value = "SELECT o.responsable.nombre, SUM(CASE WHEN o.estado = :ok THEN 1 ELSE 0 END) as quantity, SUM(CASE WHEN o.estado = :pendiente THEN 1 ELSE 0 END)  FROM OrdenesTrabajo o GROUP BY o.responsable.nombre ORDER BY quantity  DESC")
    List<String> getOrdenesTrabajoByEstado(@Param("ok") short ok, @Param("pendiente") short pendiente);


}
