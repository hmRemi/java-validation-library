package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class NotEmptyConstraint implements Constraint<String> {
    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ConstraintViolationException("String must not be empty");
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}