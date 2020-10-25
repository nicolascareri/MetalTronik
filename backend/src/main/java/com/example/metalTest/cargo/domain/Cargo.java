package com.example.metalTest.cargo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nombre_cargo;
}
