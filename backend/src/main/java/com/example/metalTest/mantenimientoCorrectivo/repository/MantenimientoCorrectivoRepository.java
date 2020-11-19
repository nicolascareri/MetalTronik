package com.example.metalTest.mantenimientoCorrectivo.repository;


import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoCorrectivoRepository extends JpaRepository<MantenimientoCorrectivo, Integer> {

    @Query(value = "SELECT CONCAT(mc.ordenTrabajo.responsable.nombre,mc.ordenTrabajo.responsable.apellido) , mc.fechaFin, mc.ordenTrabajo.fechaEntrega, SUM(CASE WHEN mc.ordenTrabajo.estado = 2 THEN 1 ELSE 0 END) FROM MantenimientoCorrectivo mc ORDER BY mc.ordenTrabajo.responsable.nombre")
    List<String> getManCor();

}
