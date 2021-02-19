package com.example.metalTest.maquina.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.maquina.service.MaquinaService;
import com.example.metalTest.parte.mapper.ParteMapper;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class   MaquinaServiceImpl implements MaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    MaquinaMapper maquinaMapper;

    @Autowired
    ParteMapper parteMapper;

    @Autowired
    ParteRepository parteRepository;
    @Autowired
    TipoRepository tipoRepository;

    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<MaquinaReducidoResponse> getAll() {
        return maquinaMapper.toMaquinaReducidoResponseList(maquinaRepository.findAll());
    }

    @Override
    public MaquinaResponse getById(Integer id) throws ValidateFieldException {
        return maquinaMapper.toMaquinaResponse((Maquina) repositoryValidator.getObject(maquinaRepository, id));

    }

    @Override
    public MaquinaResponse save(MaquinaRequest maquinaRequest) throws ValidateFieldException {
        Maquina maquina = maquinaMapper.maquinaRequestToMaquina(maquinaRequest);
        if (maquinaRepository.checkCodigoExistance(maquina.getMaquina_cod()) != 0) {
            throw new ValidateFieldException("El codigo ya tiene una maquina asociada", "maquina_cod", maquina.getMaquina_cod());
        }

        if (maquina.getEstado() != Estado.ACTIVO && maquina.getEstado() != Estado.ELIMINADO) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(maquina.getEstado()));
        }
        maquina.setPlanta(tipoRepository.findById(maquinaRequest.getPlanta_id()).get());
        maquina.setSector(tipoRepository.findById(maquinaRequest.getSector_id()).get());
        return maquinaMapper.toMaquinaResponse(maquinaRepository.save(maquina));
    }

    @Override
    public MaquinaResponse update(MaquinaRequest maquinaRequest, Integer id) throws ValidateFieldException {
        Maquina maquina = (Maquina) repositoryValidator.getObject(maquinaRepository, id);
        if (maquina.getEstado() != Estado.ACTIVO && maquina.getEstado() != Estado.ELIMINADO) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(maquina.getEstado()));
        }
        maquina.setPlanta(tipoRepository.findById(maquinaRequest.getPlanta_id()).get());
        maquina.setSector(tipoRepository.findById(maquinaRequest.getSector_id()).get());
        maquina.setMaquina_cod(maquinaRequest.getMaquina_cod());
        maquina.setEstado(maquinaRequest.getEstado());
        maquina.setEquipo(maquinaRequest.getEquipo());
        maquina.setDescripcion(maquinaRequest.getDescripcion());
        maquina.setId(id);
        return maquinaMapper.toMaquinaResponse(maquinaRepository.save(maquina));
    }

}
