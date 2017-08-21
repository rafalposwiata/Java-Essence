package io.github.rafalposwiata.java_essence.stream.parallel;

import io.github.rafalposwiata.java_essence.parallel.ForkJoinSumTask;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Rafał Poświata.
 */
public class ComputationInParallelTest {

    @Test
    public void sumUsingForkJoinFramework(){
        long[] numbers = LongStream.rangeClosed(1, 100_000).toArray();
        ForkJoinSumTask task = new ForkJoinSumTask(numbers);
        Long sum = new ForkJoinPool().invoke(task);

        Long expectedSum = 5000050000L;

        assertEquals(expectedSum, sum);
    }
}
