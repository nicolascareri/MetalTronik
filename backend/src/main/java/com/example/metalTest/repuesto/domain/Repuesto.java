package com.example.metalTest.repuesto.domain;

import com.example.metalTest.maquina.domain.Maquina;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Repuesto {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String nombre;

    @Column
    private String modelo;

    @Column
    private int cantidadInstalada;

    @JoinColumn
    @OneToOne
    private Maquina maquina;

    @Column //Codigo de barras
    private String codigoProducto;

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

}
