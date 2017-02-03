package io.github.rafalposwiata.java_essence.stream.operation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Rafał Poświata.
 */
public class NumericalStreamsTest {

    private static final int[] NUMBERS = {1, 2, 3, 4, 5};

    @Test
    public void sum(){
        int sumOfNumbers = Arrays
                .stream(NUMBERS)
                .sum();

        int expectedSumOfNumbers = 15;

        Assert.assertEquals(expectedSumOfNumbers, sumOfNumbers);
    }

    @Test
    public void max(){
         OptionalInt maxNumber = Arrays
                .stream(NUMBERS)
                .max();

        OptionalInt expectedMaxNumber = OptionalInt.of(5);

        Assert.assertEquals(expectedMaxNumber, maxNumber);
    }

    @Test
    public void range(){
        List<Integer> rangeOfNumbers = IntStream
                .range(10, 16)
                .boxed()
                .collect(toList());

        List<Integer> expectedRangeOfNumbers = Arrays.asList(10, 11, 12, 13, 14, 15);

        Assert.assertEquals(expectedRangeOfNumbers, rangeOfNumbers);
    }

    @Test
    public void rangeClosed(){
        List<Integer> closedRangeOfNumbers = IntStream
                .rangeClosed(10, 16)
                .boxed()
                .collect(toList());

        List<Integer> expectedClosedRangeOfNumbers = Arrays.asList(10, 11, 12, 13, 14, 15, 16);

        Assert.assertEquals(expectedClosedRangeOfNumbers, closedRangeOfNumbers);
    }
}
