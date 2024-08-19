package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.PastDate;
import dev.revere.validation.constraints.impl.PastDateConstraint;
import dev.revere.validation.constraints.Constraint;

import java.time.LocalDate;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class PastDateConstraintFactory implements ConstraintFactory<PastDate> {
    @Override
    public Constraint<LocalDate> create(PastDate annotation) {
        return new PastDateConstraint();
    }
}