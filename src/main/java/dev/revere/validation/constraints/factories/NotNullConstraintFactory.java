package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.NotNull;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.NotNullConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class NotNullConstraintFactory implements ConstraintFactory<NotNull> {
    @Override
    public Constraint<Object> create(NotNull annotation) {
        return new NotNullConstraint();
    }
}
