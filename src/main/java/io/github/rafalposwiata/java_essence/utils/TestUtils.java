package io.github.rafalposwiata.java_essence.utils;

import java.util.Collection;

/**
 * @author Rafał Poświata.
 */
public class TestUtils {

    public static <T> boolean containsSameElements(Collection<T> first, Collection<T> second) {
        return first == second || first.size() == second.size() && first.containsAll(second);
    }
}
