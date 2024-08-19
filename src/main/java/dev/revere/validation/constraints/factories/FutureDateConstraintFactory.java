package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.FutureDate;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.FutureDateConstraint;

import java.time.LocalDate;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class FutureDateConstraintFactory implements ConstraintFactory<FutureDate> {
    @Override
    public Constraint<LocalDate> create(FutureDate annotation) {
        return new FutureDateConstraint();
    }
}
