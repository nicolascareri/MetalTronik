package com.example.metalTest.usuarios.personal.domain;

import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Getter
@Setter
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int legajo;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fnacimiento;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn
    private Tipo cargo;

    @Column
    @Email
    private String correo_electronico;

    @OneToOne(cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JoinColumn
    private Direccion direccion;




}
