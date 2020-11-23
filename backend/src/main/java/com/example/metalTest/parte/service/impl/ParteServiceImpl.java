package com.example.metalTest.parte.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.parte.controller.request.ParteRequest;
import com.example.metalTest.parte.controller.response.ParteResponse;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.parte.mapper.ParteMapper;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParteServiceImpl implements ParteService {
    @Autowired
    ParteRepository parteRepository;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    ParteMapper parteMapper;

    @Override
    public List<ParteResponse> getAll() {
        return parteMapper.toParteResponseList(parteRepository.findAll());
    }

    @Override
    public ParteResponse create(ParteRequest parteRequest) throws ValidateFieldException {
        if (parteRepository.existsByCodigo(parteRequest.getCodigo())){
            throw new ValidateFieldException("Ya existe una parte con ese codigo", "codigo", parteRequest.getCodigo());
        }
        Parte parte = parteMapper.parteRequestToParte(parteRequest);
        ParteResponse a = parteMapper.toParteResponse(parte);
        Parte b = parteRepository.save(parte);
        a.setId(b.getId());
        return a;
    }

    @Override
    public ParteResponse getById(Integer id) throws ValidateFieldException {
        Optional<Parte> parteOptional = parteRepository.findById(id);
        if (!parteOptional.isPresent()){
            throw new ValidateFieldException("La parte que desea acceder no existe", "id", String.valueOf(id));
        }
        return parteMapper.toParteResponse(parteOptional.get());
    }

    @Override
    public List<ParteResponse> vincular(Integer id_maquina, List<Integer> idPartes) throws ValidateFieldException {
        List<Parte> parteList = parteRepository.findAllById(idPartes);
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(id_maquina);
        if (!optionalMaquina.isPresent()){
            throw new ValidateFieldException("La maquina que desea acceder no existe", "id", String.valueOf(id_maquina));
        }
        Maquina maquina = optionalMaquina.get();
        maquina.setParteList(new ArrayList<>());
        parteList.forEach(parte -> {
            parte.setMaquinaId(maquina.getId());
            parteRepository.save(parte);
        });
        maquina.setParteList(parteList);
        maquinaRepository.save(maquina);
        return parteMapper.toParteResponseList(parteList);
    }

    @Override
    public List<ParteResponse> findAllByMaquina(Integer id) {
        return parteMapper.toParteResponseList(parteRepository.getAllByMaquina(id));
    }

    @Override
    public void delete(Integer id) throws ValidateFieldException {
        Optional<Parte> parteOptional = parteRepository.findById(id);
        if (!parteOptional.isPresent()){
            throw new ValidateFieldException("La parte que desea eliminar no existe", "id", String.valueOf(id));
        }
        parteRepository.delete(parteOptional.get());
    }
}
