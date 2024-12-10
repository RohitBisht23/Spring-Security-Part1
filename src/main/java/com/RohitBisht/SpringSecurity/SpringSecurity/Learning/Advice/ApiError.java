package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime time;
    private String error;
    private HttpStatus statusCode;

    public ApiError() {
        this.time = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}
