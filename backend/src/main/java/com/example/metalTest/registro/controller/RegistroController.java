package com.example.metalTest.registro.controller;

import com.example.metalTest.registro.controller.response.RegistroResponse;
import com.example.metalTest.registro.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping("/{date}")
    public ResponseEntity<List<RegistroResponse>> getForMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(registroService.getForMonth(date), HttpStatus.OK);
    }

}
