package com.example.metalTest.almacen.asociacion.controller;

import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.almacen.asociacion.service.AsosiacionService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.maquina.domain.Maquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/repuesto")
public class AsociacionController {
   AsosiacionService asosiacionService;
    @GetMapping("/vinculados")
    public ResponseEntity<List<Asociacion>> getVinculados(){
        return new ResponseEntity<>(asosiacionService.getVinculados(), HttpStatus.OK);
    }

    @PutMapping("/vincular/maquina-parte")
    public Repuesto asociar(@Valid @RequestBody AsociarList asociarList){
        asosiacionService.asociar(asociarList);
        return null;
    }
    @GetMapping("/sin-vincular")
    public ResponseEntity<List<Maquina>> getSinVincular(){
        return new ResponseEntity<>(asosiacionService.getSinVincular(), HttpStatus.OK);
    }
}
