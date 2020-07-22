package com.example.metalTest.planta.repository;

import com.example.metalTest.planta.domain.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Integer> {

    @Query("SELECT COUNT(p) FROM Planta p WHERE lower(p.nombre) LIKE lower(:nombre)" +
            "AND (:id is NULL or p.id <>:id)")
    Long checkNombreExistance(@Param("nombre") String nombre, @Param("id") int id);
}
