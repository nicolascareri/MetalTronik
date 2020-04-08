package com.example.metalTest.tarea.mapper;

import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TareaMapper {

    Tarea tareaRequestToTarea(TareaRequest tareaRequest);

}
