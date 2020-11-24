package com.example.metalTest.usuario.domain;

import com.example.metalTest.cargo.domain.Cargo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String nombre_usuario;

    @Column
    private String contrasenia;

    @Column
    private int legajo;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fnacimiento;

    @OneToOne
    @JoinColumn
    private Cargo cargo;

    @Column
    private String correo_electronico;

    @Column
    private String pais;

    @Column
    private String provincia;

    @Column
    private String ciudad;

    @Column
    private String direccion;

    @Column
    private int dni;

    @Column
    private short estado;


}
