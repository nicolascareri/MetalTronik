package com.example.metalTest.maquina.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Maquina {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(unique = true)
    private String maquina_cod;

}
