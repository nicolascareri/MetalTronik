package com.example.metalTest.repuestomaquina.repository;

import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepuestoMaquinaRepository extends JpaRepository<RepuestoMaquina, Integer> {

    @Query("SELECT r FROM RepuestoMaquina r WHERE r.maquina.id = :id")
    Optional<RepuestoMaquina> findByMaquina(@Param("id") Integer id);

}
