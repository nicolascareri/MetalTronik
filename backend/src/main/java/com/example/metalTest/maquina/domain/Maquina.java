package com.example.metalTest.maquina.domain;

import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.sector.domain.Sector;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Maquina {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(unique = true)
    private String maquina_cod;

    @Column
    private short estado;

    @OneToOne
    @JoinColumn
    private Planta planta;

    @OneToOne
    @JoinColumn
    private Sector sector;

    @Column
    private String equipo;

    @Column
    private int nro_serie;

    @Column
    private String modelo;

    @Column
    private String descripcion;

    @Column
    private String datos_tecnicos;

}
