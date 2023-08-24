package com.example.elasticsearch.handler;

import com.example.elasticsearch.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundException(final ProductNotFoundException e) {
        log.error("ProductNotFoundException : {}",e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
