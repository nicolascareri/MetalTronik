package com.example.metalTest.preventivo.tarea.tareas.repository;

import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
}
