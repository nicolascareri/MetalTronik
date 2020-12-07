package com.example.metalTest.almacen.salida.domain;

import com.example.metalTest.almacen.movimiento.domain.Movimiento;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.usuario.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Salida extends Movimiento {
    @JoinColumn
    @OneToOne
    private Sector sector;

    @JoinColumn
    @OneToOne
    private Usuario solicitante;
}
