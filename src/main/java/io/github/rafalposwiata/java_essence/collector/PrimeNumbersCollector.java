package io.github.rafalposwiata.java_essence.collector;

import com.google.common.collect.Sets;
import io.github.rafalposwiata.java_essence.utils.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author Rafał Poświata.
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> {
            Map<Boolean, List<Integer>> result = new HashMap<>();
            result.put(TRUE, new ArrayList<>());
            result.put(FALSE, new ArrayList<>());
            return result;
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) ->
                acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(TRUE).addAll(map2.get(TRUE));
            map1.get(FALSE).addAll(map2.get(FALSE));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(IDENTITY_FINISH);
    }

    private boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return CollectionUtils
                .takeWhile(primes, prime -> prime <= candidateRoot)
                .stream()
                .noneMatch(prime -> candidate % prime == 0);
    }
}
