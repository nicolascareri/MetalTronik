package com.example.metalTest.sector.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.sector.controller.request.SectorRequest;
import com.example.metalTest.sector.domain.Sector;
import com.example.metalTest.sector.mapper.SectorMapper;
import com.example.metalTest.sector.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sector")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @Autowired
    SectorMapper sectorMapper;

    @GetMapping
    public ResponseEntity<List<Sector>> getAll(){
        return new ResponseEntity<>(sectorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sector> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(sectorService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sector> create(@RequestBody SectorRequest sectorRequest) throws ValidateFieldException {
        return new ResponseEntity<>(sectorService.create(sectorMapper.sectorRequestToSector(sectorRequest)), HttpStatus.CREATED);
    }

}
