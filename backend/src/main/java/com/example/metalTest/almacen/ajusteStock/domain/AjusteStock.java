package com.example.metalTest.almacen.ajusteStock.domain;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class AjusteStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Repuesto repuesto_id;
    @Column
    private String nombre;
    @Column
    //Codigo de barras
    private String codigo_producto;
    @Column
    private String modelo;
    @Column
    private Integer stock;
    @Column
    private Integer stock_ajustado;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fecha_correccion;
}
