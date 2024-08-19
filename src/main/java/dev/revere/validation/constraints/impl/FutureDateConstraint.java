package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

import java.time.LocalDate;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class FutureDateConstraint implements Constraint<LocalDate> {
    private final LocalDate currentDate;

    public FutureDateConstraint() {
        this.currentDate = LocalDate.now();
    }

    @Override
    public void validate(LocalDate value) throws ConstraintViolationException {
        if (value.isBefore(currentDate)) {
            throw new ConstraintViolationException("Date must be in the future");
        }
    }

    @Override
    public Class<LocalDate> getType() {
        return LocalDate.class;
    }
}