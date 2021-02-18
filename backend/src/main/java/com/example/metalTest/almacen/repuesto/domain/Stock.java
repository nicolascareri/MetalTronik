package com.example.metalTest.almacen.repuesto.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer minimo;
    @Column
    private Integer objetivo;
    @Column
    private Integer actual;
}
