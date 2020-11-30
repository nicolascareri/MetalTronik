package com.example.metalTest.preventivo.tarea.historial.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class TareaHistorial {
    @Id
    private Integer id;
    @Column
    private Integer tarea_id;
    @Column
    private String tarea;
    @Column
    private String maquina;
    @Column
    private String parte;
    @Column
    private Integer frecuencia;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date inicio;



}
