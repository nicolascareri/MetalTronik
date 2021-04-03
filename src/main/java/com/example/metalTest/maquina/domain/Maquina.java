package com.example.metalTest.maquina.domain;

import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.tipo.domain.Tipo;
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
    private Estado estado;

    @ManyToOne
    @JoinColumn
    private Tipo planta;

    @ManyToOne
    @JoinColumn
    private Tipo sector;

    @Column
    private String equipo;

    @Column
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Parte> parteList = new ArrayList<>();
}
