package org.kenne.noudybaapi.exception;

import org.kenne.noudybaapi.common.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status,
                                 WebRequest request) {
        ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
        logger.error("handleMethodArgumentNotValid error => ", ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
        logger.error("handleEntityNotFound error => ", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(org.kenne.noudybaapi.exception.EntityNotFoundException.class)
    private ResponseEntity<ErrorDto> handleEntityNotFound(org.kenne.noudybaapi.exception.EntityNotFoundException ex) {
        ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        logger.error("handleEntityNotFound error => ", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(org.kenne.noudybaapi.exception.EntityDeletionException.class)
    private ResponseEntity<ErrorDto> handleEntityNotFound(org.kenne.noudybaapi.exception.EntityDeletionException ex) {
        ErrorDto error = new ErrorDto(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex.getDetails(), HttpStatus.NOT_ACCEPTABLE.value());
        logger.error("handleEntityDeleteException error => ", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getLocalizedMessage());
        logger.error("handleAllExceptions error => ", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
