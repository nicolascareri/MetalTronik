package com.example.metalTest.tipo.repository;

import com.example.metalTest.tipo.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    @Query("Select t from Tipo t where t.tipo = :tipo")
    List<Tipo> getTipo(@Param("tipo") String tipo);

    @Query("Select t.tipo from Tipo t group by t.tipo")
    List<String> getTipos();
}
