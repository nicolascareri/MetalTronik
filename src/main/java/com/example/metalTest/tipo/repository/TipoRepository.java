package com.example.metalTest.tipo.repository;

import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.tipo.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    @Query("Select t from Tipo t where t.tipo = :tipo and t.estado = :estado")
    List<Tipo> getTipo(@Param("tipo") String tipo, @Param("estado") Estado estado);

    @Query("Select t.tipo from Tipo t where t.estado = :estado group by t.tipo ")
    List<String> getTipos(@Param("estado") Estado estado);
}
