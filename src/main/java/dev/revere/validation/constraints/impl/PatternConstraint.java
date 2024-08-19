package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class PatternConstraint implements Constraint<String> {
    private final String pattern;

    public PatternConstraint(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (!value.matches(pattern)) {
            throw new ConstraintViolationException("Value does not match the pattern");
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}