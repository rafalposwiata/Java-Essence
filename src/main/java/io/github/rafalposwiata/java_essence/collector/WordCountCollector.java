package io.github.rafalposwiata.java_essence.collector;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Rafał Poświata.
 */
public class WordCountCollector implements Collector<Character, WordCountCollector.Acc, Integer> {

    @Override
    public Supplier<Acc> supplier() {
        return () -> new Acc(0, true);
    }

    @Override
    public BiConsumer<Acc, Character> accumulator() {
        return Acc::accumulate;
    }

    @Override
    public BinaryOperator<Acc> combiner() {
        return Acc::combine;
    }

    @Override
    public Function<Acc, Integer> finisher() {
        return Acc::getValue;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet();
    }

    public class Acc {
        private int value;
        private boolean lastSpace;

        public Acc(int value, boolean lastSpace) {
            this.value = value;
            this.lastSpace = lastSpace;
        }

        public Acc combine(Acc other) {
            return new Acc(value + other.getValue(), other.lastSpace);
        }

        public void accumulate(Character character) {
            if (Character.isWhitespace(character)) {
                if (!lastSpace) {
                    lastSpace = true;
                }
            } else {
                if (lastSpace) {
                    value++;
                    lastSpace = false;
                }
            }
        }

        public int getValue() {
            return value;
        }
    }
}
