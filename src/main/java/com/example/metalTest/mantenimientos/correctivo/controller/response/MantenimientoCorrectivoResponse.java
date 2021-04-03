package com.example.metalTest.mantenimientos.correctivo.controller.response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuarios.personal.domain.Personal;
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
    private Tipo tipo;
    private int horasProduccionAfectadas;
    private String observaciones;
    private String repuestosColocados;
    private OrdenesTrabajoResponse ordenTrabajo;
    private int nrocorrectivo;
    private long tiempoReparacion;
    private Personal encargo1;
    private Personal encargo2;
    private Personal encargo3;

}
