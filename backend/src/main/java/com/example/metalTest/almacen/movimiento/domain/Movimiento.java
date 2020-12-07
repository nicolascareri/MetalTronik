package com.example.metalTest.almacen.movimiento.domain;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date fecha;

    @Column
    private int cantidad;

}
