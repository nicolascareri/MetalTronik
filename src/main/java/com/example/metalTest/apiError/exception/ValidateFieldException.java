package com.example.metalTest.apiError.exception;

public class ValidateFieldException extends Exception {
    private String field;
    private String rejectedValue;

    public ValidateFieldException(String message, String field, String rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
