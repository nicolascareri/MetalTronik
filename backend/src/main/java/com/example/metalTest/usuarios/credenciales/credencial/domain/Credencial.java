package com.example.metalTest.usuarios.credenciales.credencial.domain;

import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String nombre_usuario;

    @Column
    private String contrasenia;

    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Credencial(String nombre_usuario, String contrasenia, Set<Rol> roles) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.roles = roles;
    }
}
