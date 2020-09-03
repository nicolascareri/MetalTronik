package com.example.metalTest.repuestoMaquina.repository;

import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquinaPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoMaquinaRepository extends JpaRepository<RepuestoMaquina, RepuestoMaquinaPk> {

}
