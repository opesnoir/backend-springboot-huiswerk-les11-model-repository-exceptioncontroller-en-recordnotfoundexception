package com.example.backendjavales11huiswerkopdracht.controller;

// Spring Boot heeft een annotatie @ControllerAdvice waarmee een speciale Exception-Controller kan
//worden aangegeven. Een exception eindigt dan in een controller-methode die als response de juiste status code teruggeeft.

import com.example.backendjavales11huiswerkopdracht.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}


