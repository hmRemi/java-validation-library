package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MaxLengthConstraint implements Constraint<String> {
    private final int maxLength;

    public MaxLengthConstraint(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (value != null && value.length() > maxLength) {
            throw new ConstraintViolationException("String length must be no more than " + maxLength);
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}