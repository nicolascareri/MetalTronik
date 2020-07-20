package com.example.metalTest.mantenimientoCorrectivo.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "mantenimiento_correctivo")
@Getter
@Setter
public class MantenimientoCorrectivo {

    @Id
    @GeneratedValue
    private int id;

    @JoinColumn
    @OneToOne
    private Maquina maquina;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechainicio;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaFin;

    @Column
    private short tipofalla;

    @Column
    private int horasProduccionAfectadas;

    @Column
    private String observaciones;

    @Column
    private String repuestosColocados;

    @JoinColumn
    @OneToOne
    private OrdenesTrabajo ordenTrabajo;

    @Column
    private int nrocorrectivo;

    @Column
    private long tiempoReparacion;

    @JoinColumn
    @OneToOne
    private Usuario encargo1;

    @JoinColumn
    @OneToOne
    private Usuario encargo2;

    @JoinColumn
    @OneToOne
    private Usuario encargo3;

}
