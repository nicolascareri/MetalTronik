package com.example.metalTest.almacen.repuesto.domain;

import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquina;
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

    @Column
    private String modelo;

    @OneToMany(mappedBy = "repuestoMaquinaPk.repuesto",fetch = FetchType.LAZY , cascade = { CascadeType.MERGE })
    private List<RepuestoMaquina> repuestoMaquinaList = new ArrayList<>();

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
