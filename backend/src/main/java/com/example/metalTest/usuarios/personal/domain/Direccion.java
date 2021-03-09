package com.example.metalTest.usuarios.personal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String pais;

    @Column
    private String provincia;

    @Column
    private String ciudad;
    @Column
    private String calle;
    @Column
    private Integer numero;


    public Direccion(String pais, String provincia, String ciudad, String calle, Integer numero) {
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
    }
}
