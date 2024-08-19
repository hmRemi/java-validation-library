package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.Pattern;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.PatternConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class PatternConstraintFactory implements ConstraintFactory<Pattern> {
    @Override
    public Constraint<String> create(Pattern annotation) {
        return new PatternConstraint(annotation.value());
    }
}
