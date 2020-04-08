package com.example.metalTest.tarea.repository;

import com.example.metalTest.tarea.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    @Query("SELECT t FROM Tarea t WHERE t.usuario.id = :id")
    List<Tarea> findByUsuario(@Param("id") Integer id);
}
