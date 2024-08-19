package dev.revere.validation.assertions;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public final class Assertions {

    private Assertions() {}

    public static AssertThat assertThat() {
        return new AssertThat();
    }

    public static class AssertThat {
        public void notNull(Object obj, String message) {
            Assert.notNull(obj, message);
        }

        public void isTrue(boolean condition, String message) {
            Assert.isTrue(condition, message);
        }

        public void equals(Object expected, Object actual, String message) {
            Assert.equals(expected, actual, message);
        }
    }
}