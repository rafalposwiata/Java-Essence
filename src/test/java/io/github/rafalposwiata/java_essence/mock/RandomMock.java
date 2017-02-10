package io.github.rafalposwiata.java_essence.mock;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

/**
 * @author Rafał Poświata.
 */
public class RandomMock<T> implements Supplier<T> {

    private Queue<T> valuesQueue = new ArrayDeque<>();

    public RandomMock(T... values) {
        this(asList(values));
    }

    public RandomMock(List<T> values) {
        valuesQueue.addAll(values);
    }

    @Override
    public T get() {
        return valuesQueue.poll();
    }
}