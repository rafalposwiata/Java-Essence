package io.github.rafalposwiata.java_essence.lambda.composing;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

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

        Assert.assertEquals(new Integer(4), h1.apply(x));
        Assert.assertEquals(new Integer(3), h2.apply(x));
    }
}
