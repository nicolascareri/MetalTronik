package com.example.metalTest.almacen.movimiento.salida.controller.response;

import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuarios.personal.domain.Personal;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SalidaResponse {
    private int id;
    private Repuesto repuesto;
    private Date fecha;
    private int cantidad;
    private Tipo sector;
    private Personal solicitante;
}
