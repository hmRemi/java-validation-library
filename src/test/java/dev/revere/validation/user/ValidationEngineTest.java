package dev.revere.validation.user;


import dev.revere.validation.ValidationEngine;
import dev.revere.validation.annotations.*;
import dev.revere.validation.exceptions.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ValidationEngineTest {

    private ValidationEngine engine;

    @BeforeEach
    public void setUp() {
        engine = new ValidationEngine();
    }

    @Test
    public void testValidUser() {
        User validUser = new User("validUser", "validPass");
        assertDoesNotThrow(() -> engine.validate(validUser), "Valid user should not throw any exception.");
    }

    @Test
    public void testInvalidMinLengthUser() {
        User invalidUser = new User("us", "validPass123");
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with short username should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidMaxLengthUser() {
        User invalidUser = new User("validUser", "thisPasswordIsWayTooLong");
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with too long password should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidRangeUser() {
        RangeUser invalidUser = new RangeUser(15);
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with value out of range should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidFutureDateUser() {
        DateUser invalidUser = new DateUser(java.time.LocalDate.now().minusDays(1));
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with past date should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidPatternUser() {
        PatternUser invalidUser = new PatternUser("invalid!@#");
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with non-matching pattern should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidSizeUser() {
        SizeUser invalidUser = new SizeUser("tooLongString");
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with invalid string size should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidMaxValueUser() {
        ValueUser invalidUser = new ValueUser(150);
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with value above max should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidMinValueUser() {
        ValueUser invalidUser = new ValueUser(1);
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with value below min should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidNotEmptyUser() {
        NotEmptyUser invalidUser = new NotEmptyUser(" ");
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with empty string should throw ConstraintViolationException.");
    }

    @Test
    public void testInvalidNotNullUser() {
        NotNullUser invalidUser = new NotNullUser(null);
        assertThrows(ConstraintViolationException.class, () -> engine.validate(invalidUser),
                "User with null field should throw ConstraintViolationException.");
    }

    static class User {
        @MinLength(5)
        private final String username;
        @MaxLength(10)
        private final String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    static class RangeUser {
        @Range(min = 1, max = 10)
        private final int value;

        public RangeUser(int value) {
            this.value = value;
        }
    }

    static class DateUser {
        @FutureDate
        private final LocalDate date;

        public DateUser(LocalDate date) {
            this.date = date;
        }
    }

    static class PatternUser {
        @Pattern("^[a-zA-Z0-9]*$")
        private final String value;

        public PatternUser(String value) {
            this.value = value;
        }
    }

    static class SizeUser {
        @Size(min = 3, max = 10)
        private final String value;

        public SizeUser(String value) {
            this.value = value;
        }
    }

    static class ValueUser {
        @MaxValue(100)
        @MinValue(10)
        private final double value;

        public ValueUser(double value) {
            this.value = value;
        }
    }

    static class NotEmptyUser {
        @NotEmpty
        private final String value;

        public NotEmptyUser(String value) {
            this.value = value;
        }
    }

    static class NotNullUser {
        @NotNull
        private final String value;

        public NotNullUser(String value) {
            this.value = value;
        }
    }
}