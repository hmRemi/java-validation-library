package dev.revere.validation.constraints.factories;

import dev.revere.validation.ConstraintFactory;
import dev.revere.validation.annotations.Email;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.impl.EmailConstraint;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class EmailConstraintFactory implements ConstraintFactory<Email> {
    @Override
    public Constraint<String> create(Email annotation) {
        return new EmailConstraint();
    }

    @Override
    public Class<Email> getAnnotationType() {
        return Email.class;
    }
}
