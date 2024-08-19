package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class NotNullConstraint implements Constraint<Object> {
    @Override
    public void validate(Object value) throws ConstraintViolationException {
        if (value == null) {
            throw new ConstraintViolationException("Value cannot be null");
        }
    }

    @Override
    public Class<Object> getType() {
        return Object.class;
    }
}