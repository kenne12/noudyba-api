package org.kenne.noudybaapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
@Data
public class EntityDeletionException extends RuntimeException {

    private String details;

    public EntityDeletionException(String message) {
        super(message);
    }

    public EntityDeletionException(String message, String reason) {
        super(message);
        this.details = reason;
    }

    public EntityDeletionException(String message, Exception e) {
        super(message, e);
    }

    public EntityDeletionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
