package com.example.metalTest.preventivo.tarea.historial.controller;

import com.example.metalTest.preventivo.tarea.historial.domain.TareaHistorial;
import com.example.metalTest.preventivo.tarea.historial.service.TareaHistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preventivo/tarea-historial")
public class TareaHistorialController {
    @Autowired
    TareaHistorialService tareaHistorialService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TareaHistorial>> getAll(@PathVariable Integer id){
        return new ResponseEntity<>(tareaHistorialService.getById(id), HttpStatus.OK);
    }
}
