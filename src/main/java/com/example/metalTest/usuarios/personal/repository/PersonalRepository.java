package com.example.metalTest.usuarios.personal.repository;

import com.example.metalTest.usuarios.personal.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    List<Personal> findAllByCredencialIsNull();
    List<Personal> findAllByCredencialIsNotNull();
    @Query(value = "select p from Personal p where p.credencial is not null and p.credencial.nombre_usuario = :nombre ")
    Personal getByNombreUsuario(@Param("nombre") String nombre);


}
