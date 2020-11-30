package com.example.metalTest.correctivo.controller.response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoCorrectivoResponse {

    private int id;
    private MaquinaReducidoResponse maquina;
    private Date fechainicio;
    private Date fechaFin;
    private short tipofalla;
    private int horasProduccionAfectadas;
    private String observaciones;
    private String repuestosColocados;
    private OrdenesTrabajoResponse ordenTrabajo;
    private int nrocorrectivo;
    private long tiempoReparacion;
    private Usuario encargo1;
    private Usuario encargo2;
    private Usuario encargo3;

}
