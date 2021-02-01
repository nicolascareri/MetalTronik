package com.example.metalTest.usuarios.usuario.domain;

import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Credencial credencial;
}
