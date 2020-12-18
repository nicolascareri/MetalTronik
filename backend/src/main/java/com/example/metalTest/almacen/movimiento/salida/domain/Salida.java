package com.example.metalTest.almacen.movimiento.salida.domain;

import com.example.metalTest.almacen.movimiento.Movimiento;
import com.example.metalTest.tipo.domain.Tipo;
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
    private Tipo sector;

    @JoinColumn
    @OneToOne
    private Usuario solicitante;
}
