package io.github.rafalposwiata.java_essence.utils;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * @author Rafał Poświata.
 */
public class TestUtils {

    public static <T> void assertSameElements(Collection<T> first, Collection<T> second) {
        assertTrue(containsSameElements(first, second));
    }

    public static <T> boolean containsSameElements(Collection<T> first, Collection<T> second) {
        return first == second || first.size() == second.size() && first.containsAll(second);
    }
}
