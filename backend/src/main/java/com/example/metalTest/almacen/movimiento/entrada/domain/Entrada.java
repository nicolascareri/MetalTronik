package com.example.metalTest.almacen.movimiento.entrada.domain;

import com.example.metalTest.almacen.movimiento.Movimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Entrada extends Movimiento {


    @Column
    private String numeroOrdenCompra;

    @Column //futura entidad
    private String proveedor;
}
