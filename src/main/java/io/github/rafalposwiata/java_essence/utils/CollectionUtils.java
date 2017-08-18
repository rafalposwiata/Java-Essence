package io.github.rafalposwiata.java_essence.utils;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Rafał Poświata.
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> List<T> takeWhile(List<T> list, Predicate<T> predicate) {
        int i = 0;
        for (T item : list) {
            if (!predicate.test(item)) return list.subList(0, i);
            i++;
        }
        return list;
    }
}
