package com.example.metalTest.usuarios.credencial.repository;

import com.example.metalTest.usuarios.credencial.domain.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Integer> {
}
