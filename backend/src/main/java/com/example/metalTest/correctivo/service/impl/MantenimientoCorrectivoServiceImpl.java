package com.example.metalTest.correctivo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.correctivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.correctivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.correctivo.mapper.MantenimientoCorrectivoMapper;
import com.example.metalTest.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.correctivo.service.MantenimientoCorrectivoService;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MantenimientoCorrectivoServiceImpl implements MantenimientoCorrectivoService {

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    @Autowired
    MantenimientoCorrectivoMapper mantenimientoCorrectivoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;

    @Override
    public List<MantenimientoCorrectivoResponse> getAll() {
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponseList(mantenimientoCorrectivoRepository.findAll());
    }

    @Override
    public MantenimientoCorrectivoResponse create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException {
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);
        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(mantenimientoCorrectivoRequest.getMaquina_cod()).get());
        mantenimientoCorrectivo.setEncargo1(usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_cod()).get());
        Optional<Usuario> optionalUsuario2 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_cod());
        if (optionalUsuario2.isPresent()) {
            Usuario encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }
        Optional<Usuario> optionalUsuario3 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_cod());
        if (optionalUsuario3.isPresent()) {
            Usuario encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }
        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod());
        if (optionalOrdenesTrabajo.isPresent()) {
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }
        long diffInMillies = Math.abs(mantenimientoCorrectivoRequest.getFechaFin().getTime() - mantenimientoCorrectivoRequest.getFechainicio().getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        mantenimientoCorrectivo.setTiempoReparacion(diff);
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    @Override
    public MantenimientoCorrectivoResponse update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException {

        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);

        if (!opt.isPresent()) {
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe", "id", String.valueOf(id));

        }

        MantenimientoCorrectivo old = opt.get();

        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);

        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(mantenimientoCorrectivoRequest.getMaquina_cod()).get());
        mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod()).get());
        mantenimientoCorrectivo.setEncargo1(usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_cod()).get());

        Optional<Usuario> optionalUsuario2 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_cod());
        if (optionalUsuario2.isPresent()) {
            Usuario encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }

        Optional<Usuario> optionalUsuario3 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_cod());
        if (optionalUsuario3.isPresent()) {
            Usuario encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }

        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod());
        if (optionalOrdenesTrabajo.isPresent()) {
            OrdenesTrabajo oldOrden = old.getOrdenTrabajo();
            oldOrden.setEstado(EstadoOrden.PENDIENTE.getValue());
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }
        long diffInMillies = Math.abs(mantenimientoCorrectivoRequest.getFechaFin().getTime() - mantenimientoCorrectivoRequest.getFechainicio().getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        mantenimientoCorrectivo.setTiempoReparacion(diff);
        mantenimientoCorrectivo.setId(id);
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    @Override
    public MantenimientoCorrectivoResponse getById(Integer id) throws ValidateFieldException {
        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe", "id", String.valueOf(id));
        }
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(opt.get());
    }

    @Override
    public List<IndicatorResponse> getIndicatorsManCorUsuario() {
        return null;
    }


}
