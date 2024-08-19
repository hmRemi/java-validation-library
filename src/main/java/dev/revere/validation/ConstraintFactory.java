package dev.revere.validation;

import dev.revere.validation.constraints.Constraint;

import java.lang.annotation.Annotation;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public interface ConstraintFactory<A extends Annotation> {
    Constraint<?> create(A annotation);
    Class<A> getAnnotationType();
}