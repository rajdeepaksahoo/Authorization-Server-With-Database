package com.db.authserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserIsAlreadyRegisteredException.class)
    public ResponseEntity<Map<String,Object>> userAlreadyRegisteredException(UserIsAlreadyRegisteredException exception){
        Map<String,Object> response = new HashMap<>();
        response.put("Status","Failed");
        response.put("Reason","User is already registered...");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
