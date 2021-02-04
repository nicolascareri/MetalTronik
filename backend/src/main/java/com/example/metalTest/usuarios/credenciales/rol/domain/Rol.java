package com.example.metalTest.usuarios.credenciales.rol.domain;
import com.example.metalTest.usuarios.credenciales.permiso.domain.Permiso;

import com.example.metalTest.usuarios.credenciales.rol.enums.RolRango;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolRango rango;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Permiso> permisos;
}
