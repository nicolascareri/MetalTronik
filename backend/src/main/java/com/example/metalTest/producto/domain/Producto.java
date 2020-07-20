package com.example.metalTest.producto.domain;

import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue
    private int id;

    @JoinColumn
    @OneToOne
    private RepuestoMaquina repuestoMaquina;

    @Column
    private int precio;

    @Column
    private int existencia;

    @Column
    private String unidad;

    @Column
    private int puntoPedido;

    @Column
    private int stockObjetivo;

    @Column
    private short tipoRepuesto;

    @Column
    private String ubicacion;

}
