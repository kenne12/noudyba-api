package org.kenne.noudybaapi.exception;

public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {

    private String reason;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
