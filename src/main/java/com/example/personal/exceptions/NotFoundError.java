package com.example.personal.exceptions;

public class NotFoundError extends Exception {
    private String id;
    private String type;

    public NotFoundError(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getMessage() {
        return "Could not find " + type + " with ID " + id;
    }
}
