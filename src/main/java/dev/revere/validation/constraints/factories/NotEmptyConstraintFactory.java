package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.NotEmpty;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.NotEmptyConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class NotEmptyConstraintFactory implements ConstraintFactory<NotEmpty> {
    @Override
    public Constraint<String> create(NotEmpty annotation) {
        return new NotEmptyConstraint();
    }

    @Override
    public Class<NotEmpty> getAnnotationType() {
        return NotEmpty.class;
    }
}