package com.example.metalTest.tipo.repository;

import com.example.metalTest.tipo.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
