package com.ifgoiano.topfilmes.domain.domainException;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DomainExceptionHandler {
    @ExceptionHandler(BusinessRulesException.class)
    public ResponseEntity<Problem> handleBusinessRulesException(BusinessRulesException ex) {

        var status = HttpStatus.BAD_REQUEST;

        var problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle(ex.getMessage());
        problem.setDateHour(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(problem);
    }
}
