package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.Range;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.RangeConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class RangeConstraintFactory implements ConstraintFactory<Range> {
    @Override
    public Constraint<Object> create(Range annotation) {
        return new RangeConstraint(annotation.min(), annotation.max());
    }
}
