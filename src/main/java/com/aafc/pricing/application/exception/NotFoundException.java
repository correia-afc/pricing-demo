package com.aafc.pricing.application.exception;

/**
 * Custom unchecked exception representing a resource not found scenario.
 * This exception can be thrown to indicate that a requested resource was not
 * found in the system.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the not found scenario.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
