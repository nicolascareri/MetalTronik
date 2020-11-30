package com.example.metalTest.preventivo.tarea.tareas.mapper;

import com.example.metalTest.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.preventivo.tarea.tareas.controller.request.TareasRequest;
import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TareasMapper {
    Tareas tareaRequestToTarea(TareasRequest tareasRequest);

    TareasResponse toTareaResponse(Tareas tarea);

    List<TareasResponse> toTareaResponseList(List<Tareas> tareaList);
}
