package io.github.rafalposwiata.java_essence.stream.operation;

import io.github.rafalposwiata.java_essence.model.PythagoreanTriple;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author Rafał Poświata.
 */
public class PythagoreanTripleTest {

    @Test
    public void generatePythagoreanTriples() {
        int maxNumber = 10;
        List<PythagoreanTriple> pythagoreanTriples = IntStream
                .rangeClosed(1, maxNumber)
                .boxed()
                .flatMap(a -> IntStream
                        .rangeClosed(a, maxNumber)
                        .mapToObj(b -> new PythagoreanTriple(a, b))
                )
                .filter(PythagoreanTriple::isValid)
                .collect(toList());

        List<PythagoreanTriple> expectedPythagoreanTriples = asList(
                new PythagoreanTriple(3, 4, 5),
                new PythagoreanTriple(6, 8, 10)
        );

        Assert.assertEquals(expectedPythagoreanTriples, pythagoreanTriples);
    }
}
