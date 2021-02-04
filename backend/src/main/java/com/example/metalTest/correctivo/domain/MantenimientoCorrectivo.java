package com.example.metalTest.correctivo.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Getter
@Setter
public class MantenimientoCorrectivo{

    @Id
    @GeneratedValue
    private int id;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Maquina maquina;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Parte parte;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechainicio;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaFin;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Tipo tipo;

    @Column
    private int horasProduccionAfectadas;

    @Column
    private String observaciones;

    @Column
    private String repuestosColocados;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private OrdenesTrabajo ordenTrabajo;

    @Column
    private int nrocorrectivo;

    @Column
    private int tiempoReparacion;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Personal encargo1;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Personal encargo2;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Personal encargo3;



}
