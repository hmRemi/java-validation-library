package dev.revere.validation.constraints;

import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public interface Constraint<T> {
    void validate(T value) throws ConstraintViolationException;
    Class<T> getType();
}
