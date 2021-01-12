package com.example.metalTest.almacen.ajusteStock.repository;

import com.example.metalTest.almacen.ajusteStock.domain.AjusteStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteRepository extends JpaRepository<AjusteStock, Integer> {
}
