package com.example.metalTest.correctivo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.correctivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.correctivo.service.MantenimientoCorrectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mantenimiento-correctivo")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class MantenimientoCorrectivoController {

    @Autowired
    MantenimientoCorrectivoService mantenimientoCorrectivoService;

    @GetMapping
    public ResponseEntity<List<MantenimientoCorrectivoResponse>> getAll() {
        return new ResponseEntity<>(mantenimientoCorrectivoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoCorrectivoResponse> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoCorrectivoResponse> update(@Valid @RequestBody MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.update(mantenimientoCorrectivoRequest, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MantenimientoCorrectivoResponse> create(@Valid @RequestBody MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(mantenimientoCorrectivoService.create(mantenimientoCorrectivoRequest), HttpStatus.CREATED);
    }


}
