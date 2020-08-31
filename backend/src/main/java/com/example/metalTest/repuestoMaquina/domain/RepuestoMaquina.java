package com.example.metalTest.repuestoMaquina.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.repuesto.domain.Repuesto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "repuesto_maquina")
public class RepuestoMaquina {

    @EmbeddedId
    private RepuestoMaquinaPk repuestoMaquinaPk;

    @Column
    private Integer cantidad_instalada;
}
