package com.herculanoleo.springexample.configuration;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.springexample.models.dtos.web.ServerErrorResponse;
import com.herculanoleo.springexample.models.dtos.web.ServerValidationErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ValidatorException.class })
    public ResponseEntity<ServerValidationErrorResponse> validator(ValidatorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ServerValidationErrorResponse(OffsetDateTime.now(), ex.getMessage(), ex.getFieldErrors()));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ServerErrorResponse> generic(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerErrorResponse(OffsetDateTime.now(), ex.getMessage()));
    }

}
