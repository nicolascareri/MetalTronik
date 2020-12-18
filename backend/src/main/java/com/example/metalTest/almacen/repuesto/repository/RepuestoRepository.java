package com.example.metalTest.almacen.repuesto.repository;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {
}
