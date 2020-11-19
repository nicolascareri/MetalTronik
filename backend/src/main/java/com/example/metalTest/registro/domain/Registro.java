package com.example.metalTest.registro.domain;

import com.example.metalTest.tarea.domain.Tarea;
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
    @OneToOne
    @JoinColumn
    private Tarea tarea;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date fechaPlanificada;
    @Column
    private Boolean realizo;
    @Column
    private String observaciones;
}
