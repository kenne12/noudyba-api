package org.kenne.noudybaapi.exception;

public class MemberDeleteException extends RuntimeException {

    private String reason;

    public MemberDeleteException(String message) {
        super(message);
    }

    public MemberDeleteException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public MemberDeleteException(String message, Exception e) {
        super(message, e);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
