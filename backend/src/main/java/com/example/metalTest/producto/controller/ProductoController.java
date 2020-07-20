package com.example.metalTest.producto.controller;

import com.example.metalTest.producto.controller.request.ProductoRequest;
import com.example.metalTest.producto.domain.Producto;
import com.example.metalTest.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        return new ResponseEntity<>(productoService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody ProductoRequest productoRequest){
        return new ResponseEntity<>(productoService.create(productoRequest), HttpStatus.CREATED.);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update (@Valid @RequestBody ProductoRequest productoRequest, @PathVariable Integer id){
        return new ResponseEntity<>(productoService.update(productoRequest, id), HttpStatus.OK);
    }

}
