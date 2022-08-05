package com.mck.task01.resources.exceptions;

import com.mck.task01.servicies.exceptions.DataBaseException;
import com.mck.task01.servicies.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now()
                , status.value()
                ,"Resource not found"
                , e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataBaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(Instant.now()
                , status.value()
                ,"Resource not found"
                , e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
