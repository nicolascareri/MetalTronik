package com.example.metalTest.tarea.domain;

import com.example.metalTest.maquina.domain.Maquina;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Tarea {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String tarea;

    @JoinColumn
    @OneToOne
    private Maquina maquina;

    @Column
    private int frecuencia;

    @Column
    private Date inicio;

    @Column
    private Short estado;
}
