package com.example.metalTest.almacen.CoreccionStockHistorial.controller;

import com.example.metalTest.almacen.CoreccionStockHistorial.controller.request.CorrHistRequest;
import com.example.metalTest.almacen.CoreccionStockHistorial.controller.response.CorrHistResponse;
import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;
import com.example.metalTest.almacen.CoreccionStockHistorial.service.CorreccionHistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/ajustes")
public class CorrecHistController {
    @Autowired
    CorreccionHistService correccionHistService;
    @GetMapping
    public ResponseEntity<List<CorrHistResponse>> getAll() {
        return new ResponseEntity<>(correccionHistService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CorrHistResponse> create(@Valid @RequestBody  CorrHistRequest corrHistRequest, @PathVariable Integer id){
        return new ResponseEntity<>(correccionHistService.create(corrHistRequest, id), HttpStatus.CREATED);
    }

}
