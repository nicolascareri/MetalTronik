package com.example.metalTest.tarea.mapper;

import com.example.metalTest.tarea.controller.Response.TareaResponse;
import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    Tarea tareaRequestToTarea(TareaRequest tareaRequest);

    TareaResponse toTareaResponse(Tarea tarea);

    List<TareaResponse> toTareaResponseList(List<Tarea> tareaList);
}
