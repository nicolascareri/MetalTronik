package com.example.metalTest.almacen.salida.repository;

import com.example.metalTest.almacen.salida.domain.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Integer> {
}
