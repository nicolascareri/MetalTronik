package com.example.metalTest.repuestoMaquina.domain;

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
