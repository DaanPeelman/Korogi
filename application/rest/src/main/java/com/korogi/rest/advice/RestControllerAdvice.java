package com.korogi.rest.advice;

import static com.korogi.dto.ErrorDTO.newErrorDTO;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.korogi.dto.ErrorDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorDTO errorDTO = newErrorDTO()
                .status(NOT_FOUND.getReasonPhrase())
                .code(NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDTO, NOT_FOUND);
    }
}