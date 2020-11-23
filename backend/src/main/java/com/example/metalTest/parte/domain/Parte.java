package com.example.metalTest.parte.domain;

import com.example.metalTest.maquina.domain.Maquina;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Parte {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String codigo;

    @Column
    private Integer maquinaId;
}
