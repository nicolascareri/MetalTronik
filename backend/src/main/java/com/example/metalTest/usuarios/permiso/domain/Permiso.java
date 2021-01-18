package com.example.metalTest.usuarios.permiso.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Permiso {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column
    private String sector;
    @Column
    private String permiso;
}
