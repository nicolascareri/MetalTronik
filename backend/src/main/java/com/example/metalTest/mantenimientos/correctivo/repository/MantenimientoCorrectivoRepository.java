package com.example.metalTest.mantenimientos.correctivo.repository;


import com.example.metalTest.mantenimientos.correctivo.domain.MantenimientoCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoCorrectivoRepository extends JpaRepository<MantenimientoCorrectivo, Integer> {

    @Query(value = "SELECT CONCAT(mc.ordenTrabajo.responsable.nombre,mc.ordenTrabajo.responsable.apellido) , mc.fechaFin, mc.ordenTrabajo.fechaEntrega, SUM(CASE WHEN mc.ordenTrabajo.estado = 2 THEN 1 ELSE 0 END) FROM MantenimientoCorrectivo mc ORDER BY mc.ordenTrabajo.responsable.nombre")
    List<String> getManCor();
    @Query(nativeQuery = true, value = "SELECT mac.maquina_cod, count(*) totales, sum(cast(m.fecha_fin as date) - cast(m.fechainicio as date))  as dias,extract('month' from m.fecha_fin) AS \"month\" \n" +
            "FROM mantenimiento_correctivo m inner join maquina mac on mac.id = m.maquina_id \n" +
            "where extract('year' from now()) = extract('year' from m.fecha_fin)\n" +
            "group by mac.maquina_cod,\"month\"")
    List<String[]> getPromHorasMantenimientoMaquina();

}
