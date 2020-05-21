package com.example.metalTest.sector.repository;

import com.example.metalTest.sector.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {

    @Query("Select s FROM Sector s ORDER BY s.descripcion")
    List<Sector> getAllSector();


    @Query("SELECT COUNT(s) FROM Sector s WHERE lower(s.descripcion) LIKE lower(:descripcion)" +
            "AND (:id is NULL or s.id <>:id)")
    Long checkDescripcionExistance(@Param("descripcion") String descripcion, @Param("id") Integer id);

}
