package com.example.metalTest.parte.repository;

import com.example.metalTest.parte.domain.Parte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParteRepository extends JpaRepository<Parte, Integer> {
    @Query("SELECT p FROM Parte p WHERE p.maquinaId = :id")
    List<Parte> getAllByMaquina(@Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM Parte p WHERE p.codigo = :codigo")
    Boolean existsByCodigo(@Param("codigo") String codigo);
}
