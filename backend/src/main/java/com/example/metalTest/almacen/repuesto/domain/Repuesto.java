package com.example.metalTest.almacen.repuesto.domain;
import com.example.metalTest.tipo.domain.Tipo;
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


    @Column //Codigo de barras
    private String codigoProducto;


    @Column
    private String modelo;

    @Column
    private String marca;

    @Column
    private int precio_unitario;
    @Column
    private int precio_total;

    @Column
    private int existencia;

    @Column
    private String unidad;

    @Column //stock minimo
    private int puntoPedido;

    @Column
    private int stockObjetivo;

    @JoinColumn
    @OneToOne
    private Tipo tipo_repuesto;

    @Column
    private String ubicacion;

    @Column
    private int cantidad_instalada;



}
