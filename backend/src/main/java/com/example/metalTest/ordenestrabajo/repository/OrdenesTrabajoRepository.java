package com.example.metalTest.ordenestrabajo.repository;

import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenesTrabajoRepository extends JpaRepository<OrdenesTrabajo, Integer> {
    @Query("select o from OrdenesTrabajo o order by o.maquina.sector.id")
    List<OrdenesTrabajo> getBySectorOrdenado();

}
