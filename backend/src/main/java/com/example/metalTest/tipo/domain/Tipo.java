package com.example.metalTest.tipo.domain;

import com.example.metalTest.common.estado.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tipo {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nombre;

    @Column //tipo al que pertenece preventivo/correctivo etc
    private String tipo;

    @Column
    private Estado estado;


}
