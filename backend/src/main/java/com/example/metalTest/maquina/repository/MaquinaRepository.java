package com.example.metalTest.maquina.repository;

import com.example.metalTest.maquina.domain.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {

    @Query("SELECT COUNT(m) FROM Maquina m WHERE m.maquina_cod = :cod")
    Long checkCodigoExistance(@Param("cod") String cod);

}
