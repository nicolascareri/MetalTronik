package com.example.metalTest.preventivo.tarea.tareas.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.preventivo.tarea.historial.domain.TareaHistorial;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String tarea;

    @JoinColumn
    @OneToOne
    private Maquina maquina;

    @JoinColumn
    @OneToOne
    private Parte parte;

    @Column
    private int frecuencia;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date inicio;

    @Column
    private Short estado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TareaHistorial> historialList;
}
