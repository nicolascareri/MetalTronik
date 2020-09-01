package com.example.metalTest.maquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.maquina.service.MaquinaService;
import com.example.metalTest.planta.repository.PlantaRepository;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.sector.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements MaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    MaquinaMapper maquinaMapper;

    @Autowired
    PlantaRepository plantaRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Override
    public List<MaquinaReducidoResponse> getAll() {
        return maquinaMapper.toMaquinaReducidoResponseList(maquinaRepository.findAll());
    }

    @Override
    public MaquinaResponse getById(Integer id) throws ValidateFieldException {
        Optional<Maquina> opt = maquinaRepository.findById(id);
        if (opt.isPresent()) {
            return maquinaMapper.toMaquinaResponse(opt.get());
        } else {
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", id.toString());
        }

    }

    @Override
    public Maquina save(MaquinaRequest maquinaRequest) throws ValidateFieldException {
        Maquina maquina = maquinaMapper.maquinaRequestToMaquina(maquinaRequest);
        List<RepuestoMaquina> repuestoMaquinaList = new ArrayList<>();
        maquina.setRepuestoMaquinaList(repuestoMaquinaList);
        if (maquinaRepository.checkCodigoExistance(maquina.getMaquina_cod()) != 0) {
            throw new ValidateFieldException("El codigo ya tiene una maquina asociada", "maquina_cod", maquina.getMaquina_cod());
        }

        if (maquina.getEstado() != Estado.ACTIVO.getValue() && maquina.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(maquina.getEstado()));

        }
        maquina.setPlanta(plantaRepository.findById(maquinaRequest.getPlanta_cod()).get());
        maquina.setSector(sectorRepository.findById(maquinaRequest.getSector_cod()).get());
        return maquinaRepository.save(maquina);
    }

    @Override
    public Maquina update(MaquinaRequest maquinaRequest, Integer id) throws ValidateFieldException {
        Optional<Maquina> op = maquinaRepository.findById(id);
        if (!op.isPresent()) {
            throw new ValidateFieldException("El usuario que desea acceder no existe", "id", String.valueOf(id));
        }
        Maquina maquina = op.get();
        if (maquina.getEstado() != Estado.ACTIVO.getValue() && maquina.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(maquina.getEstado()));
        }
        maquina.setPlanta(plantaRepository.findById(maquinaRequest.getPlanta_cod()).get());
        maquina.setSector(sectorRepository.findById(maquinaRequest.getSector_cod()).get());
        maquina.setId(id);
        return maquinaRepository.save(maquina);
    }

}
