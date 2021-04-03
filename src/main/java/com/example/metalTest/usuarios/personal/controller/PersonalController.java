package com.example.metalTest.usuarios.personal.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> getAll() {
        return new ResponseEntity<>(personalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(personalService.getById(id), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Personal> updatePersonal(@Valid @RequestBody PersonalRequest personalRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(personalService.update(id, personalRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPersonal(@Valid @RequestBody PersonalRequest personalRequest, BindingResult bindingResult) throws ValidateFieldException {
        return new ResponseEntity<>(personalService.create(personalRequest), HttpStatus.CREATED);
    }


}
