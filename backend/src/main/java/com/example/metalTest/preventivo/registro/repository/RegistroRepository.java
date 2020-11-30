package com.example.metalTest.preventivo.registro.repository;

import com.example.metalTest.preventivo.registro.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    @Query(nativeQuery = true, value =
            "SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Registro r WHERE EXTRACT(MONTH from r.fecha_planificada) = EXTRACT(month from current_date) and EXTRACT(YEAR from r.fecha_planificada) = EXTRACT(YEAR from current_date)")
    Boolean existsPlanificacionActual();

    @Query(nativeQuery = true, value =
            "SELECT * FROM Registro r WHERE EXTRACT(MONTH from r.fecha_planificada) = :month and EXTRACT(YEAR from r.fecha_planificada) = :year")
    List<Registro> getByDate(@Param("year") int year,@Param("month") int month);
}
