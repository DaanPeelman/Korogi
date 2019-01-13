package com.korogi.rest.advice;

import static com.korogi.dto.ErrorDTO.newErrorDTO;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.UUID;
import com.korogi.dto.ErrorDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorDTO errorDTO = newErrorDTO()
                .status(NOT_FOUND.getReasonPhrase())
                .code(NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDTO, NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleThrowable(Throwable throwable) {
        String logCode = UUID.randomUUID().toString();

        log.error("Unexpected error: '" + logCode + "'", throwable);

        ErrorDTO errorDTO = newErrorDTO()
                .status(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred. If this problem keeps persisting please contact our technical support with code: '" + logCode + "'.")
                .build();

        return new ResponseEntity<>(errorDTO, INTERNAL_SERVER_ERROR);
    }
}