package com.example.metalTest.cargo.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.controller.request.CargoRequest;
import com.example.metalTest.cargo.domain.Cargo;
import com.example.metalTest.cargo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    @Autowired
    CargoService cargoService;
    @GetMapping
    public ResponseEntity<List<Cargo>> getAll() {
        return new ResponseEntity<>(cargoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(cargoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cargo> create(@Valid @RequestBody CargoRequest cargoRequest) throws ValidateFieldException {
        return new ResponseEntity<>(cargoService.create(cargoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> update(@Valid @RequestBody CargoRequest cargoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(cargoService.update(cargoRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            cargoService.remove(id);
            return ResponseEntity.ok().body("Eliminado Cargo id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo no encontrado");
        }
    }
}
