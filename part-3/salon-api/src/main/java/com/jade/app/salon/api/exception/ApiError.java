package com.jade.app.salon.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/7/22
 */
@ControllerAdvice
public class ApiError {

    @ExceptionHandler(SalonException.class)
    public ResponseEntity<?> badSalonRequest(SalonException salonException) {
        return ResponseEntity.badRequest().body(salonException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> badSalonRequest(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
