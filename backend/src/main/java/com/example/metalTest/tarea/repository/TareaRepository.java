package com.example.metalTest.tarea.repository;

import com.example.metalTest.tarea.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository  extends JpaRepository<Tarea, Integer> {
}
