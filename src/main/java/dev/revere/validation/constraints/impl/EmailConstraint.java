package dev.revere.validation.constraints.impl;

import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;

import java.util.regex.Pattern;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class EmailConstraint implements Constraint<String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    @Override
    public void validate(String value) throws ConstraintViolationException {
        if (value == null || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new ConstraintViolationException("Invalid email address");
        }
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}