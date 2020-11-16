package com.example.metalTest.ordenestrabajo.repository;

import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenesTrabajoRepository extends JpaRepository<OrdenesTrabajo, Integer> {

    @Query(value = "SELECT CONCAT(o.responsable.nombre, o.responsable.apellido) , SUM(CASE WHEN o.estado = :ok THEN 1 ELSE 0 END) as quantity, SUM(CASE WHEN o.estado = :pendiente THEN 0 ELSE 1 END)  FROM OrdenesTrabajo o GROUP BY o.responsable.nombre ORDER BY quantity  DESC")
    List<String> getOrdenesTrabajoByUsuarios(@Param("ok") short ok, @Param("pendiente") short pendiente);


    @Query(value = "SELECT o.maquina.sector.id as sector, SUM(CASE WHEN o.estado = :ok THEN 1 ELSE 0 END) as quantity, SUM(CASE WHEN o.estado = :pendiente THEN 0 ELSE 1 END)  FROM OrdenesTrabajo o GROUP BY sector ORDER BY quantity  DESC")
    List<String> getOrdenesTrabajoBySectores(@Param("ok") short ok, @Param("pendiente") short pendiente);


}
