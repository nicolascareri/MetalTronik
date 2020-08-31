package com.example.metalTest.movimiento.domain;

import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.usuario.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Movimiento {
    @Id
    @GeneratedValue
    private int id;

    @JoinColumn
    @OneToOne
    private Repuesto repuesto;

    @Column
    private Date fecha;

    @Column
    private int cantidad;

}
