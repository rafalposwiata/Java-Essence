package io.github.rafalposwiata.java_essence.utils;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * @author Rafał Poświata.
 */
public class TestUtils {

    private static final String MESSAGE_TEMPLATE = "expected same:<%s> was not:<%s>";

    public static <T> void assertSameElements(Collection<T> expected, Collection<T> actual) {
        String message = createMessage(expected, actual);
        boolean containsSameElements = containsSameElements(expected, actual);
        assertTrue(message, containsSameElements);
    }

    public static <T> boolean containsSameElements(Collection<T> expected, Collection<T> actual) {
        return expected == actual || expected.size() == actual.size() && expected.containsAll(actual);
    }

    public static String createMessage(Object expected, Object actual) {
        return createMessage(MESSAGE_TEMPLATE, expected, actual);
    }

    public static String createMessage(String messageTemplate, Object expected, Object actual) {
        return String.format(messageTemplate, expected, actual);
    }
}
