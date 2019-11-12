package com.example.personal.exceptions;

import java.util.List;

public class BadRequestError extends Exception {
    private List<String> missing;
    private String type;
    private String message;

    public BadRequestError(List<String> missing, String type) {
        this.missing = missing;
        this.type = type;
    }

    public BadRequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        if (message == null) {
            String concat = "";
            for (int i = 0; i < missing.size(); i++) {
                if (i != missing.size() - 1)
                    concat += missing.get(i) + ", ";
                else
                    concat += "and " + missing.get(i);
            }

            return "Missing parameters " + concat + " for type " + type;
        } else {
            return message;
        }
    }
}
