package com.example.metalTest.usuarios.personal.mapper;

import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TipoRepository.class)
public interface PersonalMapper {

    Personal personalRequestToPersonal(PersonalRequest personalRequest);

}
