package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MinLengthConstraint implements Constraint<String> {
    private final int minLength;

    public MinLengthConstraint(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (value == null || value.length() < minLength) {
            throw new ConstraintViolationException("String length must be at least " + minLength);
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}