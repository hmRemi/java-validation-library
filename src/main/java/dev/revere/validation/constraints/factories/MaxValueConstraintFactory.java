package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.MaxValue;
import dev.revere.validation.constraints.impl.MaxValueConstraint;
import dev.revere.validation.constraints.Constraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MaxValueConstraintFactory implements ConstraintFactory<MaxValue> {
    @Override
    public Constraint<Double> create(MaxValue annotation) {
        return new MaxValueConstraint(annotation.value());
    }
}
