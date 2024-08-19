package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MaxValueConstraint implements Constraint<Double> {
    private final double maxValue;

    public MaxValueConstraint(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void validate(Double value) throws ConstraintViolationException {
        if (value > maxValue) {
            throw new ConstraintViolationException("Value must be no more than " + maxValue);
        }
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}