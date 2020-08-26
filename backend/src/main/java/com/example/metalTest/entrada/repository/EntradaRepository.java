package com.example.metalTest.entrada.repository;

import com.example.metalTest.entrada.domain.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
}
