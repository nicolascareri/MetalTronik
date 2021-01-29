package com.example.metalTest.usuarios.personal.repository;

import com.example.metalTest.usuarios.personal.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {


    @Query("select u from Personal u where u.credencial.nombre_usuario = :nombre_usuario")
    Optional<Personal> findByCredencial_Nombre_usuario(@Param("nombre_usuario") String nombre_usuario);

}
