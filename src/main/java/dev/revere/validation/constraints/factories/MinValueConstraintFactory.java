package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.MinValue;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.MinValueConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MinValueConstraintFactory implements ConstraintFactory<MinValue> {
    @Override
    public Constraint<Double> create(MinValue annotation) {
        return new MinValueConstraint(annotation.value());
    }

    @Override
    public Class<MinValue> getAnnotationType() {
        return MinValue.class;
    }
}