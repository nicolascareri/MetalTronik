package com.example.metalTest.mantenimientoCorrectivo.repository;


import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoCorrectivoRepository extends JpaRepository<MantenimientoCorrectivo, Integer> {
    @Query(value = "SELECT  mc.ordenTrabajo.responsable.nombre, mc.fechaFin, mc.ordenTrabajo.fechaEntrega  FROM MantenimientoCorrectivo mc ")
    List<String> getManCor();
}
