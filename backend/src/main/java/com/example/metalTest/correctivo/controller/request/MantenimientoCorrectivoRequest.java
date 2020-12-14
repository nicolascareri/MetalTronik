package com.example.metalTest.correctivo.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class MantenimientoCorrectivoRequest {

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina_cod;

    private Integer parte_cod;

    @NotNull
    private Date fechainicio;

    @NotNull
    private Date fechaFin;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private int tipo_id;

    @NotNull
    private int horasProduccionAfectadas;

    private String observaciones;

    private String repuestosColocados;

    private int ordenTrabajo_cod;

    @NotNull
    private int nrocorrectivo;

    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo1_cod;

    private int encargo2_cod;

    private int encargo3_cod;
}
