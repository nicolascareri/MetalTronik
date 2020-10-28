package com.example.metalTest.tarea.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.tareaTipo.domain.TareaTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date inicio;

    @JoinColumn
    @OneToOne
    private TareaTipo tareaTipo;

    @Column
    private Short estado;
}
