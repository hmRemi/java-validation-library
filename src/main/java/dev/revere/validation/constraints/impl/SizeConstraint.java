package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class SizeConstraint implements Constraint<String> {
    private final int min;
    private final int max;

    public SizeConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (value.length() < min || value.length() > max) {
            throw new ConstraintViolationException(String.format("Value must be between %d and %d characters", min, max));
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}