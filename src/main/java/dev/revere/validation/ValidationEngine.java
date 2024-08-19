package dev.revere.validation;

import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ValidationEngine {
    private final ConstraintProcessor processor = new ConstraintProcessor();

    public <T> void validate(T instance) throws ConstraintViolationException, IllegalAccessException {
        if (instance == null) {
            throw new ConstraintViolationException("Instance cannot be null");
        }
        processor.applyConstraints(instance);
    }
}