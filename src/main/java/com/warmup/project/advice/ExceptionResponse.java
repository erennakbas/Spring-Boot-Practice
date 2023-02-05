package com.warmup.project.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ExceptionResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }


}
