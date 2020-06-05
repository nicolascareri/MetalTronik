package com.example.metalTest.ordenestrabajo.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "ordenes_trabajo")
public class OrdenesTrabajo {
    @Id
    @GeneratedValue
    @Column
    private int ordentrabajo_cod;

    @OneToOne
    @JoinColumn
    private Planta planta;

    @OneToOne
    @JoinColumn
    private Maquina maquina;

    @Column
    private String pedidoMateriales;

    @Column
    private String tarea;

    @Column
    private short priodidad;

    @Column
    private short tipo;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaEntrega;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaRealizar;

    @OneToOne
    @JoinColumn
    private Usuario encargo;

    @OneToOne
    @JoinColumn
    private Usuario responsable;

    @Column
    private short estado;

    @OneToOne
    @JoinColumn
    private Sector sector;

    @Column
    private String observaciones;

    @Column
    private int ordenTerciarizacion;

}
