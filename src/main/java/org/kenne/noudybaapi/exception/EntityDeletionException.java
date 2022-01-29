package org.kenne.noudybaapi.exception;

public class EntityDeletionException extends RuntimeException {

    private String reason;

    public EntityDeletionException(String message) {
        super(message);
    }

    public EntityDeletionException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public EntityDeletionException(String message, Exception e) {
        super(message, e);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
