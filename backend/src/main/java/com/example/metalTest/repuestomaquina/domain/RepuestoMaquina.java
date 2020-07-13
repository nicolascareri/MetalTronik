package com.example.metalTest.repuestomaquina.domain;

import com.example.metalTest.maquina.domain.Maquina;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class RepuestoMaquina {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String nombre;

    @Column
    private String modelo;

    @Column
    private int cantidadInstalada;

    @JoinColumn
    @OneToOne
    private Maquina maquina;

}
