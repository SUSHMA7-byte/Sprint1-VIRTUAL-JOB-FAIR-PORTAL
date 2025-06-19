// ValidationException.java
package com.sprint1.exception;

import java.util.List;

public class ValidationException extends Exception {
    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super(String.join("; ", errors)); // combines all messages
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
