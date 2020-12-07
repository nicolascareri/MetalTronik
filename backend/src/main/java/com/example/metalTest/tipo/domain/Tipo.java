package com.example.metalTest.tipo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Tipo {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nombre;

    @Column //tipo al que pertenece preventivo/correctivo etc
    private String tipo;


}
