package com.example.metalTest.apiError.handler;

import com.example.metalTest.apiError.controller.response.ValidateFieldResponse;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidateFieldExceptionHandler {
    @ExceptionHandler(value = {ValidateFieldException.class})
    public ResponseEntity<ValidateFieldResponse> HandleValidateFieldException(ValidateFieldException ex) {
        ValidateFieldResponse validateFieldResponse = new ValidateFieldResponse(ex.getMessage(), ex.getField(),
                ex.getRejectedValue());
        return new ResponseEntity<>(validateFieldResponse, HttpStatus.BAD_REQUEST);
    }

}
