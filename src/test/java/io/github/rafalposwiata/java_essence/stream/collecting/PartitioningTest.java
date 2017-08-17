package io.github.rafalposwiata.java_essence.stream.collecting;

import com.google.common.collect.Lists;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.utils.TestUtils.assertSameElements;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Rafał Poświata.
 */
public class PartitioningTest {

    @Test
    public void partitioningByHeight() {
        Predicate<Person> isTall = person -> person.getHeight() > 1.60;

        Map<Boolean, List<Person>> tallPeople = ALL_PEOPLE
                .stream()
                .collect(partitioningBy(isTall));

        List<Person> expectedTallPeople = Lists.newArrayList(TOM_BERG, TOM_LEE, MEGAN_CLARK);
        List<Person> expectedSmallPeople = Lists.newArrayList(JULIA_CLARK);

        assertSameElements(expectedTallPeople, tallPeople.get(TRUE));
        assertSameElements(expectedSmallPeople, tallPeople.get(FALSE));
    }

    @Test
    public void partitioningNumbersIntoPrimeAndNonprime() {
        Predicate<Integer> isPrime = candidate -> {
            int candidateRoot = (int) Math.sqrt((double) candidate);
            return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
        };

        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, 10)
                .boxed()
                .collect(partitioningBy(isPrime));

        List<Integer> expectedPrimeNumbers = Lists.newArrayList(2, 3, 5, 7);
        List<Integer> expectedNonprimeNumbers = Lists.newArrayList(4, 6, 8, 9, 10);

        assertSameElements(expectedPrimeNumbers, partitionPrimes.get(TRUE));
        assertSameElements(expectedNonprimeNumbers, partitionPrimes.get(FALSE));
    }
}
