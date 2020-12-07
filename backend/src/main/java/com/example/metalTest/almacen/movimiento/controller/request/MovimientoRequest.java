package com.example.metalTest.almacen.movimiento.controller.request;

import com.example.metalTest.common.conditional.Conditional;
import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Conditional(selected = "tipoMovimiento", values = {"1"}, required = {"numeroOrdenCompra", "proveedor"})
@Conditional(selected = "tipoMovimiento", values = {"2"}, required = {"sector_cod", "solicitante_cod"})
public class  MovimientoRequest {

    @NotNull
    @ValidEntity(repository = RepuestoRepository.class)
    private int repuesto_cod;

    @NotNull
    @Min(1)
    @Max(2)
    private short tipoMovimiento;

    @NotNull
    private int precio;

    @NotNull
    private Date fecha;

    private String numeroOrdenCompra;

    private String proveedor;

    @NotNull //existencia
    private int cantidad;

    @ValidEntity(repository = SectorRepository.class)
    private int sector_cod;

    @ValidEntity(repository = UsuarioRepository.class)
    private int solicitante_cod;
}
