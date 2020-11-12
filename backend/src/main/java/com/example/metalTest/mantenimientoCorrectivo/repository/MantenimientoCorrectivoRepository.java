package com.example.metalTest.mantenimientoCorrectivo.repository;


import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoCorrectivoRepository extends JpaRepository<MantenimientoCorrectivo, Integer> {

}
