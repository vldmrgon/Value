package com.my.pro.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.my.pro.exception.ValidationBusinessException;
import com.my.pro.exception.PurchaseBusinessException;
import com.my.pro.exception.ShopperBusinessException;
import com.my.pro.exception.ProductBusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleGeneralException(Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

    @ExceptionHandler(ValidationBusinessException.class)
    public ResponseEntity<String> handleValidationException(ValidationBusinessException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> String.format("Field %s %s", error.getField(), error.getDefaultMessage()))
                .orElse("A field not valid");
        return handleException(message);
    }

    @ExceptionHandler({ShopperBusinessException.class, ProductBusinessException.class, PurchaseBusinessException.class})
    public ResponseEntity<String> handleBusinessException(RuntimeException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> handleException(Throwable e, HttpStatus status) {
        return ResponseEntity.status(status).body(e.getMessage());
    }

    private ResponseEntity<String> handleException(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}