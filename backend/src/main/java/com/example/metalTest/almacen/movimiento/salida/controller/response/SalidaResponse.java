package com.example.metalTest.almacen.movimiento.salida.controller.response;

import com.example.metalTest.almacen.repuesto.controller.response.RepuestoReducidoResponse;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuario.domain.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SalidaResponse {
    private int id;
    private RepuestoReducidoResponse repuesto;
    private Date fecha;
    private int cantidad;
    private Tipo sector;
    private Usuario solicitante;
}
