package com.example.metalTest.almacen.asociacion.repository;

import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.maquina.domain.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsociacionRepository extends JpaRepository<Asociacion, Integer> {

    @Query("SELECT m FROM Maquina m WHERE m.maquina_cod not in (select a.maquina_codigo from Asociacion a) ")
    List<Maquina> getSinAsociar();
}
