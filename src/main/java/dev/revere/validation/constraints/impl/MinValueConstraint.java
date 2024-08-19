package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MinValueConstraint implements Constraint<Double> {
    private final double minValue;

    public MinValueConstraint(double minValue) {
        this.minValue = minValue;
    }

    @Override
    public void validate(Double value) throws ConstraintViolationException {
        if (value < minValue) {
            throw new ConstraintViolationException("Value must be at least " + minValue);
        }
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}