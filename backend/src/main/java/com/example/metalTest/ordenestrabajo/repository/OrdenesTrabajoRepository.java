package com.example.metalTest.ordenestrabajo.repository;

import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface OrdenesTrabajoRepository extends JpaRepository<OrdenesTrabajo, Integer> {

    @Query(value = "SELECT t.nombre, count(*) FROM ordenes_trabajo o inner join tipo t on o.tipo_id = t.id group by t.nombre", nativeQuery = true)
    List<String[]>  getOrdenesTrabajoByTipo();
    @Query(value = "SELECT t.nombre, count(*) FROM ordenes_trabajo o inner join prioridades t on o.prioridad_id = t.id group by t.nombre", nativeQuery = true)
    List<String[]>  getOrdenesTrabajoByPrioridad();


    @Query(value = "SELECT  u.nombre, count(*) as totales, count(case when o.estado = 2 then 1 end) as enOk FROM ordenes_trabajo o inner join usuario u on u.id = o.responsable_id group by u.nombre", nativeQuery = true)
    List<String[]>  getOrdenesFormula1Usuario();

    @Query(value = "SELECT s.descripcion, count(*) as totales, count(case when o.estado = 2 then 1 end) as enOk FROM ordenes_trabajo o inner join maquina m on m.id = o.maquina_id inner join sector s on s.id = m.sector_id group by s.descripcion", nativeQuery = true)
   List<String[]>  getOrdenesFormula1Sector();
}
