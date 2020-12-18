package com.example.metalTest.almacen.repuesto.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Repuesto {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String nombre;


    @Column //Codigo de barras
    private String codigoProducto;


    @Column
    private String modelo;

    @Column
    private String marca;

    @Column
    private int precio;

    @Column
    private int existencia;

    @Column
    private String unidad;

    @Column //stock minimo
    private int puntoPedido;

    @Column
    private int stockObjetivo;

    @Column
    private short tipoRepuesto;

    @Column
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "maquina")
    private Maquina maquina;

    @ManyToOne
    @JoinColumn(name = "parte")
    private Parte parte;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    CantidadInstalada cantidadInstalada;

}
