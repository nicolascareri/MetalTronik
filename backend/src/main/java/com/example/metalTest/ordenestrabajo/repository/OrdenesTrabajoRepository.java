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
    @Query("select o from OrdenesTrabajo o order by o.maquina.sector.id")
    List<OrdenesTrabajo> getBySectorOrdenado();
    @Query(value = "SELECT t.nombre, count(*) FROM ordenes_trabajo o inner join tipo t on o.tipo_id = t.id group by t.nombre", nativeQuery = true)
    List<String[]>  getOrdenesTrabajoByTipo();


}
