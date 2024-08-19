package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class RangeConstraint implements Constraint<Object> {
    private final double min;
    private final double max;

    public RangeConstraint(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(Object value) throws ConstraintViolationException {
        if (value == null) {
            throw new ConstraintViolationException("Value cannot be null");
        }

        double doubleValue;
        if (value instanceof Number) {
            doubleValue = ((Number) value).doubleValue();
        } else {
            throw new ConstraintViolationException("Unsupported type: " + value.getClass().getName());
        }

        if (doubleValue < min || doubleValue > max) {
            throw new ConstraintViolationException(String.format("Value must be between %.2f and %.2f", min, max));
        }
    }

    @Override
    public Class<Object> getType() {
        return Object.class;
    }
}