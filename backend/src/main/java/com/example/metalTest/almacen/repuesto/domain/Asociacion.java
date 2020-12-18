package com.example.metalTest.almacen.repuesto.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "asociaciones")
@Getter
@Setter
public class Asociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Maquina maquina;

    @ManyToOne
    @JoinColumn
    private Parte parte;

    @ManyToOne
    @JoinColumn
    private Repuesto repuesto;

    @Column
    private Integer cantidad;

}
