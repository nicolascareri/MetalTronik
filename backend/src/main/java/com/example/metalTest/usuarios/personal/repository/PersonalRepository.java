package com.example.metalTest.usuarios.personal.repository;

import com.example.metalTest.usuarios.personal.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    List<Personal> findAllByCredencialIsNull();


}
