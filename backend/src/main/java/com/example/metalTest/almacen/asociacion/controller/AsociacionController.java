package com.example.metalTest.almacen.asociacion.controller;

import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.almacen.asociacion.service.AsosiacionService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/asociacion")
public class AsociacionController {
    @Autowired
    AsosiacionService asosiacionService;

    @GetMapping("/vinculados")
    public ResponseEntity<List<Asociacion>> getVinculados(){
        return new ResponseEntity<>(asosiacionService.getVinculados(), HttpStatus.OK);
    }

    @PostMapping("/vincular/maquina-parte")
    public Repuesto asociar(@Valid @RequestBody AsociarList asociarList) throws ValidateFieldException {
        asosiacionService.asociar(asociarList);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsociacionResponse> update(@Valid @RequestBody AsociarList asociarList, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(asosiacionService.update(asociarList, id), HttpStatus.OK);
    }

    @GetMapping("/sin-vincular")
    public ResponseEntity<List<Maquina>> getSinVincular(){
        return new ResponseEntity<>(asosiacionService.getSinVincular(), HttpStatus.OK);
    }
}
