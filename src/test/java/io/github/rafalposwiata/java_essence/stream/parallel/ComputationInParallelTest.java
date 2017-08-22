package io.github.rafalposwiata.java_essence.stream.parallel;

import io.github.rafalposwiata.java_essence.collector.WordCountCollector;
import io.github.rafalposwiata.java_essence.parallel.ForkJoinSumTask;
import io.github.rafalposwiata.java_essence.spliterator.WordSpliterator;
import org.junit.Test;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

/**
 * @author Rafał Poświata.
 */
public class ComputationInParallelTest {

    @Test
    public void sumUsingForkJoinFramework() {
        long[] numbers = LongStream.rangeClosed(1, 100_000).toArray();
        ForkJoinSumTask task = new ForkJoinSumTask(numbers);
        Long sum = new ForkJoinPool().invoke(task);

        Long expectedSum = 5000050000L;

        assertEquals(expectedSum, sum);
    }

    @Test
    public void countingWordsUsingCustomSpliterator() {
        String sentence = "The Lord of the  Rings is an epic        high fantasy        novel written by " +
                "English author and scholar     J. R. R. Tolkien. ";

        Spliterator<Character> spliterator = new WordSpliterator(sentence);
        Stream<Character> characters = StreamSupport.stream(spliterator, true);
        Integer count = characters
                .parallel()
                .collect(new WordCountCollector());

        Integer expectedCount = 21;

        assertEquals(expectedCount, count);
    }
}
