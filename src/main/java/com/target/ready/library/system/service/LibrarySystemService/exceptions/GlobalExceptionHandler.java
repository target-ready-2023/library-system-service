package com.target.ready.library.system.service.LibrarySystemService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> ResourceExistsException(ResourceAlreadyExistsException ex){
        ExceptionResponse exceptionResponse=new ExceptionResponse(ex.getMessage(),HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(exceptionResponse.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFound(ResourceNotFoundException ex){
        ExceptionResponse exceptionResponse=new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponse.getMessage(),HttpStatus.NOT_FOUND);
    }
}
