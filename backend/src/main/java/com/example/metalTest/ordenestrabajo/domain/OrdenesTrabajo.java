package com.example.metalTest.ordenestrabajo.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuarios.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Getter
@Setter
@Table(name = "ordenes_trabajo")
public class OrdenesTrabajo implements Comparable<OrdenesTrabajo>{
    @Id
    @GeneratedValue
    @Column
    private int ordentrabajo_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Parte parte;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Maquina maquina;

    @Column
    private String pedidoMateriales;

    @Column
    private String tarea;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Tipo prioridad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Tipo tipo;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    @Getter
    @Setter
    private Date fechaEntrega;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaRealizar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Usuario encargo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @Getter
    @Setter
    private Usuario responsable;

    @Column
    private short estado;

    @Column
    private String observaciones;

    @Column
    private int ordenTerciarizacion;




    @Override
    public int compareTo(OrdenesTrabajo o) {
        return this.responsable.getNombre().compareTo(o.getResponsable().getNombre());
    }

}
