package dev.revere.validation.user;

import dev.revere.validation.ConstraintProcessor;
import dev.revere.validation.annotations.*;
import dev.revere.validation.exceptions.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ConstraintProcessorTest {

    private final ConstraintProcessor processor = new ConstraintProcessor();

    @Test
    public void testMinLengthConstraintValid() {
        User validUser = new User("validUser", "valid");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "Valid user should not throw any exception.");
    }

    @Test
    public void testMinLengthConstraintInvalid() {
        User invalidUser = new User("us", "short");
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with short username should throw ConstraintViolationException.");
    }

    @Test
    public void testMaxLengthConstraintValid() {
        User validUser = new User("validUser", "validPass");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "Valid user should not throw any exception.");
    }

    @Test
    public void testMaxLengthConstraintInvalid() {
        User invalidUser = new User("validUser", "thisPasswordIsWayTooLong");
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with too long password should throw ConstraintViolationException.");
    }

    @Test
    public void testRangeConstraintValid() {
        RangeUser validUser = new RangeUser(5);
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "Valid user with value in range should not throw any exception.");
    }

    @Test
    public void testRangeConstraintInvalid() {
        RangeUser invalidUser = new RangeUser(15);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with value out of range should throw ConstraintViolationException.");
    }

    @Test
    public void testFutureDateConstraintValid() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        DateUser validUser = new DateUser(futureDate);
        assertDoesNotThrow(() -> processor.applyConstraints(validUser),
                "User with future date should not throw any exception.");
    }

    @Test
    public void testFutureDateConstraintInvalid() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        DateUser invalidUser = new DateUser(pastDate);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with past date should throw ConstraintViolationException.");
    }

    @Test
    public void testPastDateConstraintValid() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        PastDateUser validUser = new PastDateUser(pastDate);
        assertDoesNotThrow(() -> processor.applyConstraints(validUser),
                "User with past date should not throw any exception.");
    }

    @Test
    public void testPastDateConstraintInvalid() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        PastDateUser invalidUser = new PastDateUser(futureDate);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with future date should throw ConstraintViolationException.");
    }

    @Test
    public void testPatternConstraintValid() {
        PatternUser validUser = new PatternUser("valid123");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with matching pattern should not throw any exception.");
    }

    @Test
    public void testPatternConstraintInvalid() {
        PatternUser invalidUser = new PatternUser("invalid!@#");
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with non-matching pattern should throw ConstraintViolationException.");
    }

    @Test
    public void testSizeConstraintValid() {
        SizeUser validUser = new SizeUser("valid");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with valid string size should not throw any exception.");
    }

    @Test
    public void testSizeConstraintInvalid() {
        SizeUser invalidUser = new SizeUser("tooLongString");
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with invalid string size should throw ConstraintViolationException.");
    }

    @Test
    public void testMaxValueConstraintValid() {
        ValueUser validUser = new ValueUser(50.0);
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with value below max should not throw any exception.");
    }

    @Test
    public void testMaxValueConstraintInvalid() {
        ValueUser invalidUser = new ValueUser(150.0);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with value above max should throw ConstraintViolationException.");
    }

    @Test
    public void testMinValueConstraintValid() {
        ValueUser validUser = new ValueUser(10.0);
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with value above min should not throw any exception.");
    }

    @Test
    public void testMinValueConstraintInvalid() {
        ValueUser invalidUser = new ValueUser(1.0);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with value below min should throw ConstraintViolationException.");
    }

    @Test
    public void testNotEmptyConstraintValid() {
        NotEmptyUser validUser = new NotEmptyUser("non-empty");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with non-empty string should not throw any exception.");
    }

    @Test
    public void testNotEmptyConstraintInvalid() {
        NotEmptyUser invalidUser = new NotEmptyUser(" ");
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
                "User with empty string should throw ConstraintViolationException.");
    }

    @Test
    public void testNotNullConstraintValid() {
        NotNullUser validUser = new NotNullUser("not null");
        assertDoesNotThrow(() -> processor.applyConstraints(validUser), "User with non-null field should not throw any exception.");
    }

    @Test
    public void testNotNullConstraintInvalid() {
        NotNullUser invalidUser = new NotNullUser(null);
        assertThrows(ConstraintViolationException.class, () -> processor.applyConstraints(invalidUser),
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
        private final Integer value;

        public RangeUser(Integer value) {
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

    static class PastDateUser {
        @PastDate
        private final LocalDate date;

        public PastDateUser(LocalDate date) {
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
        private final Double value;

        public ValueUser(Double value) {
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