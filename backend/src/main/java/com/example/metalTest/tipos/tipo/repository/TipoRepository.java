package com.example.metalTest.tipos.tipo.repository;

import com.example.metalTest.tipos.tipo.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
