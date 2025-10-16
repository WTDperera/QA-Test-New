package com.example.expensetrackerbackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // මේක global exception handler එකක් කියලා Spring වලට කියනවා
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) // IllegalArgumentException එකක් ආවොත් මේ method එක run වෙන්න
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}