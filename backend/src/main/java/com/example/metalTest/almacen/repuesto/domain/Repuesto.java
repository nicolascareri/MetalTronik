package com.example.metalTest.almacen.repuesto.domain;
import com.example.metalTest.tipo.domain.Tipo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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


    @Column(unique = true) //Codigo de barras
    private String codigo_producto;


    @Column
    private String modelo;

    @Column
    private String marca;

    @Column
    private int precio_unitario;
    @Column
    private int precio_total;

    @Column
    private String unidad;


    @JoinColumn
    @ManyToOne(cascade = {CascadeType.ALL})
    private Tipo tipo_repuesto;

    @OneToOne(cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JoinColumn
    private Stock stock;

    @Column
    private String ubicacion;

    @Column
    private int cantidad_instalada;



}
