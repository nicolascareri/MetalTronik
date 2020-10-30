package com.example.metalTest.cargo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String nombre_cargo;
}
