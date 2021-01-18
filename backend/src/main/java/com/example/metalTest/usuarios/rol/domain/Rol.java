package com.example.metalTest.usuarios.rol.domain;
import com.example.metalTest.usuarios.permiso.domain.Permiso;
import com.example.metalTest.tipo.domain.Tipo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Permiso> permisos;
}
