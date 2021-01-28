package com.example.metalTest.usuarios.rol.domain;
import com.example.metalTest.usuarios.permiso.domain.Permiso;
import com.example.metalTest.tipo.domain.Tipo;

import com.example.metalTest.usuarios.rol.enums.RolRango;
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
