package com.example.metalTest.almacen.CoreccionStockHistorial.domain;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class CorreccionHistorial {
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
    private String codigoProducto;
    @Column
    private String modelo;
    @Column
    private Integer stock;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fecha_correccion;
}
