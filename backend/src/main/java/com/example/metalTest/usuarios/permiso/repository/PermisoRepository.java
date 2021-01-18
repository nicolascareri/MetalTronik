package com.example.metalTest.usuarios.permiso.repository;

import com.example.metalTest.usuarios.permiso.domain.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso,Integer> {
}
