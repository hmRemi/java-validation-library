package dev.revere.validation.assertions;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public final class Assert {

    private Assert() {}

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void equals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new IllegalArgumentException(message);
        }
    }
}