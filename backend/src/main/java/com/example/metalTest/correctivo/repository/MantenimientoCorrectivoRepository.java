package com.example.metalTest.correctivo.repository;


import com.example.metalTest.correctivo.domain.MantenimientoCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoCorrectivoRepository extends JpaRepository<MantenimientoCorrectivo, Integer> {

    @Query(value = "SELECT CONCAT(mc.ordenTrabajo.responsable.nombre,mc.ordenTrabajo.responsable.apellido) , mc.fechaFin, mc.ordenTrabajo.fechaEntrega, SUM(CASE WHEN mc.ordenTrabajo.estado = 2 THEN 1 ELSE 0 END) FROM MantenimientoCorrectivo mc ORDER BY mc.ordenTrabajo.responsable.nombre")
    List<String> getManCor();

    @Query(value = "SELECT mac.maquina_cod, count(*) totales,  sum(m.fecha_fin::date -  m.fechainicio::date)  as dias, EXTRACT(MONTH FROM m.fecha_fin::date) FROM mantenimiento_correctivo m inner join maquina mac on mac.id = m.maquina_id group by mac.maquina_cod, EXTRACT(MONTH FROM m.fecha_fin::date)", nativeQuery = true)
    List<String> getPromHorasMantenimientoMaquina();

}
