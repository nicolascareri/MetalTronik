package com.example.metalTest.maquina.domain;

import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.sector.domain.Sector;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String descripcion;

    @OneToMany(mappedBy = "repuestoMaquinaPk.maquina", fetch = FetchType.LAZY ,cascade = { CascadeType.ALL })
    private List<RepuestoMaquina> repuestoMaquinaList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parte> parteList = new ArrayList<>();
}
