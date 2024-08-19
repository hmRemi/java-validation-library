package dev.revere.validation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {
    int value();
}