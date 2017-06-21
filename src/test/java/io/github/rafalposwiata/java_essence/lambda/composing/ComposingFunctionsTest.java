package io.github.rafalposwiata.java_essence.lambda.composing;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * @author Rafał Poświata.
 */
public class ComposingFunctionsTest {

    @Test
    public void andThenVsCompose() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g); // h1(x) = g(f(x))
        Function<Integer, Integer> h2 = f.compose(g); // h2(x) = f(g(x))

        Integer x = 1;

        assertEquals(new Integer(4), h1.apply(x));
        assertEquals(new Integer(3), h2.apply(x));
    }
}
