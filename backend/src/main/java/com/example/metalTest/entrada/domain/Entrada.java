package com.example.metalTest.entrada.domain;

import com.example.metalTest.movimiento.domain.Movimiento;
import com.example.metalTest.repuesto.domain.Repuesto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Entrada extends Movimiento {

    @Column
    private int precio;

    @Column
    private String numeroOrdenCompra;

    @Column //futura entidad
    private String proveedor;
}
