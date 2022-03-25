package org.kenne.noudybaapi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private HttpStatus httpStatus;
    private Integer statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorDto(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public ErrorDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public ErrorDto(HttpStatus httpStatus, String message, String details, int statusCode) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }

    public ErrorDto(HttpStatus httpStatus, String message, int statusCode) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.statusCode = statusCode;
    }
}
