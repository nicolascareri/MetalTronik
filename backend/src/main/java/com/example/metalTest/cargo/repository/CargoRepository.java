package com.example.metalTest.cargo.repository;

import com.example.metalTest.cargo.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
