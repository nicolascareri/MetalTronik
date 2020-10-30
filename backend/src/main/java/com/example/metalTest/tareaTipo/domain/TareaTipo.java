package com.example.metalTest.tareaTipo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TareaTipo {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String nombre;

}
