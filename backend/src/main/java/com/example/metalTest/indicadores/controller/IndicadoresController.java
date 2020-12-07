package com.example.metalTest.indicadores.controller;

import com.example.metalTest.indicadores.controller.response.Torta;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.correctivo.service.MantenimientoCorrectivoService;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * formula 1 (Ordenes en ok / Ordenes totales) * 100.
 * formula 2 (Fecha entrega (De Ordenes) - Fecha de fin (Correctivo)) / Ordenes en ok
 * Formula 3 (Ordenes atrasadas / Ordenes pendientes) * 100
 */
@RestController
@RequestMapping("/api/indicadores/formula/")
public class IndicadoresController {

    @Autowired
    IndicadoresService indicadoresService;

    @Autowired
    MantenimientoCorrectivoService mantenimientoCorrectivoService;


    @GetMapping("1/usuario")
    public ResponseEntity<List<IndicatorResponse>> getIndForm1Usuario() {
        return new ResponseEntity<>(indicadoresService.getIndicatorsForm1Usuario(), HttpStatus.OK);
    }
    @GetMapping("1/sector")
    public ResponseEntity<List<IndicatorResponse>> getIndForm1Sector() {
        return new ResponseEntity<>(indicadoresService.getIndicatorsForm1Sector(), HttpStatus.OK);
    }
    @GetMapping("2/usuario")
    public ResponseEntity<List<IndicatorResponse>> getIndForm2Usuario(){
        return new ResponseEntity<>(indicadoresService.getIndicatorForm2Usuario(), HttpStatus.OK);
    }
    @GetMapping("2/sector")
    public ResponseEntity<List<IndicatorResponse>> getIndForm2Sector() {
        return new ResponseEntity<>(indicadoresService.getIndicatorForm2Sector(), HttpStatus.OK);
    }
    @GetMapping("Torta/tipo")
    public ResponseEntity<Torta> getGrafTortaTipo() {
        return new ResponseEntity<>(indicadoresService.getGrafTortaTipo(), HttpStatus.OK);
    }
    @GetMapping("Torta/prioridad")
    public ResponseEntity<Torta> getGrafTortaPrioridad() {
        return new ResponseEntity<>(indicadoresService.getGrafTortaPrioridad(), HttpStatus.OK);
    }




}
