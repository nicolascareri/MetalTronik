package com.example.metalTest.almacen.repuesto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CantidadInstalada")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CantidadInstalada {
    @Id
    @GeneratedValue
    @Column(name = "id_cantidad")
    private Integer id;

    @Column
    private Integer cantidad_instalada;

    public CantidadInstalada(Integer a){
        cantidad_instalada = a;
    }

}
