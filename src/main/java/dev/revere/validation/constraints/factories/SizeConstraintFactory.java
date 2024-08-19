package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.Size;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.SizeConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class SizeConstraintFactory implements ConstraintFactory<Size> {
    @Override
    public Constraint<String> create(Size annotation) {
        return new SizeConstraint(annotation.min(), annotation.max());
    }
}