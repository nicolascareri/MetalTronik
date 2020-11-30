package com.example.metalTest.preventivo.tarea.historial.repository;

import com.example.metalTest.preventivo.tarea.historial.domain.TareaHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaHistorialRepository  extends JpaRepository<TareaHistorial, Integer> {
    @Query("SELECT th from TareaHistorial th where th.tarea_id = :id ")
    List<TareaHistorial> findByTarea(@Param("id") Integer id);
}
