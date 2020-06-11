package com.example.metalTest.prioridades.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Prioridades {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nombre;

    @Column
    private short estado;
}
