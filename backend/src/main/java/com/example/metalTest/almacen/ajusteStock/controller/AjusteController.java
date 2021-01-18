package com.example.metalTest.almacen.ajusteStock.controller;

import com.example.metalTest.almacen.ajusteStock.controller.request.AjusteRequest;
import com.example.metalTest.almacen.ajusteStock.controller.response.AjusteResponse;
import com.example.metalTest.almacen.ajusteStock.service.AjusteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/ajustes")
public class AjusteController {
    @Autowired
    AjusteService ajusteService;
    @GetMapping
    public ResponseEntity<List<AjusteResponse>> getAll() {
        return new ResponseEntity<>(ajusteService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AjusteResponse> create(@Valid @RequestBody AjusteRequest ajusteRequest, @PathVariable Integer id){
        return new ResponseEntity<>(ajusteService.create(ajusteRequest, id), HttpStatus.CREATED);
    }

}
