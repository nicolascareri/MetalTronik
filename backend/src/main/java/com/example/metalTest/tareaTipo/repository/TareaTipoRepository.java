package com.example.metalTest.tareaTipo.repository;

import com.example.metalTest.tareaTipo.domain.TareaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaTipoRepository  extends JpaRepository<TareaTipo, Integer> {
}
