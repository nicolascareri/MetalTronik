package com.example.metalTest.movimiento.domain;

import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.usuario.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Movimiento {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int precio;

    @Column
    private Date fecha;

    @Column
    private String numeroOrdenCompra;

    @Column //futura entidad
    private String proveedor;

    @Column
    private int cantidad;

    @JoinColumn
    @OneToOne
    private Sector sector;

    @JoinColumn
    @OneToOne
    private Usuario solicitante;
}
