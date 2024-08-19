package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.MinLength;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.MinLengthConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MinLengthConstraintFactory implements ConstraintFactory<MinLength> {
    @Override
    public Constraint<String> create(MinLength annotation) {
        return new MinLengthConstraint(annotation.value());
    }
}