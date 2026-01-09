package com.digitalwallet.walletservice.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private List<FieldError> errors;

    public ErrorResponse() { }

    public ErrorResponse(LocalDateTime timestamp, int status, String message, List<FieldError> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // getters & setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<FieldError> getErrors() { return errors; }
    public void setErrors(List<FieldError> errors) { this.errors = errors; }

    public static class FieldError {
        private String field;
        private String error;

        public FieldError() {}
        public FieldError(String field, String error) {
            this.field = field;
            this.error = error;
        }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
