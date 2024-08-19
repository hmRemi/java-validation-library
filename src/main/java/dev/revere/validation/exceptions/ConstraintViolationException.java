package dev.revere.validation.exceptions;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ConstraintViolationException extends ValidationException {
    public ConstraintViolationException(String message) {
        super(message);
    }
}