package com.example.metalTest.usuarios.credenciales.permiso.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.metalTest.tipo.domain.Tipo;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permiso {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Tipo sector;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Tipo permiso;

    public Permiso(Tipo permiso_tipo, Tipo sector_tipo){
        sector = sector_tipo;
        permiso = permiso_tipo;
    }
}
