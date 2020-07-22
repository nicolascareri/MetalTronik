package com.example.metalTest.repuesto.repository;

import com.example.metalTest.repuesto.domain.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    @Query("SELECT r FROM Repuesto r WHERE r.maquina.id = :id")
    List<Repuesto> findByMaquina(@Param("id") Integer id);

}
