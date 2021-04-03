package com.example.metalTest.usuarios.credenciales.rol.repository;

import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRango(String nombre);
}
