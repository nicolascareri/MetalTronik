package com.example.metalTest.preventivo.registro.domain;

import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Registro {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Tareas tarea;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date fechaPlanificada;
    @Column
    private Boolean realizo;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date fechaRealizada;
    @Column
    private String observaciones;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Personal encargado;
}
