package com.example.metalTest.almacen.repuestoMaquina.repository;

import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquinaPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoMaquinaRepository extends JpaRepository<RepuestoMaquina, RepuestoMaquinaPk> {

}
