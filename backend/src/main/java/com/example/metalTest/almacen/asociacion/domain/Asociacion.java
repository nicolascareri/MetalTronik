package com.example.metalTest.almacen.asociacion.domain;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "asociaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column
    private String  maquina_codigo;
    @Column
    private String maquina_sector;
    @Column
    private String maquina_planta;
    @Column
    private String repuesto_nombre;
    @Column
    private String repuesto_modelo;
    @Column
    private Integer cantidad_instalada;
    @Column
    private String parte_nombre;
    @Column
    private String observaciones;

}
