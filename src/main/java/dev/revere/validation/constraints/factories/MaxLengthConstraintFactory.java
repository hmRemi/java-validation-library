package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.MaxLength;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.MaxLengthConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class MaxLengthConstraintFactory implements ConstraintFactory<MaxLength> {
    @Override
    public Constraint<String> create(MaxLength annotation) {
        return new MaxLengthConstraint(annotation.value());
    }

    @Override
    public Class<MaxLength> getAnnotationType() {
        return MaxLength.class;
    }
}
