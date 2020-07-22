package com.example.metalTest.sector.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String descripcion;

    @Column
    private short estado;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date fechaAlta;

}
