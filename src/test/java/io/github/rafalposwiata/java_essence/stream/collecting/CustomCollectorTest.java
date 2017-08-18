package io.github.rafalposwiata.java_essence.stream.collecting;

import com.google.common.collect.Lists;
import io.github.rafalposwiata.java_essence.collector.PrimeNumbersCollector;
import io.github.rafalposwiata.java_essence.collector.ToListCollector;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static io.github.rafalposwiata.java_essence.data.People.ALL_PEOPLE;
import static io.github.rafalposwiata.java_essence.utils.TestUtils.assertSameElements;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Rafał Poświata.
 */
public class CustomCollectorTest {

    @Test
    public void customToListCollector() {
        List<String> peopleNames = ALL_PEOPLE
                .stream()
                .map(Person::getName)
                .collect(new ToListCollector<>());

        List<String> expectedPeopleNames = Lists.newArrayList("Tom", "Tom", "Megan", "Julia");

        assertSameElements(expectedPeopleNames, peopleNames);
    }

    @Test
    public void customPrimeNumbersCollector() {
        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, 10)
                .boxed()
                .collect(new PrimeNumbersCollector());

        List<Integer> expectedPrimeNumbers = Lists.newArrayList(2, 3, 5, 7);
        List<Integer> expectedNonprimeNumbers = Lists.newArrayList(4, 6, 8, 9, 10);

        assertSameElements(expectedPrimeNumbers, partitionPrimes.get(TRUE));
        assertSameElements(expectedNonprimeNumbers, partitionPrimes.get(FALSE));
    }
}
