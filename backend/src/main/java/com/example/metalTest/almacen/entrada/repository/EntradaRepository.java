package com.example.metalTest.almacen.entrada.repository;

import com.example.metalTest.almacen.entrada.domain.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
}
