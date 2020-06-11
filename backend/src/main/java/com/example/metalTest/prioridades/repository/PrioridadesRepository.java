package com.example.metalTest.prioridades.repository;

import com.example.metalTest.prioridades.domain.Prioridades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadesRepository extends JpaRepository<Prioridades, Integer> {
}
