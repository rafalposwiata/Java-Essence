package io.github.rafalposwiata.java_essence.stream.collecting;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static java.util.stream.Collectors.*;

/**
 * @author Rafał Poświata.
 */
public class ReducingAndSummarizingTest {

    private static final double DELTA = 0.1;

    @Test
    public void collectorsCounting() {
        long numberOfWomen = ALL_PEOPLE
                .stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .collect(counting());

        long expectedNumberOfWoman = 2;

        Assert.assertEquals(expectedNumberOfWoman, numberOfWomen);
    }

    @Test
    public void collectorsMaxBy() {
        Comparator<Person> weightComparator = Comparator.comparing(Person::getWeight);

        Person personWithHighestWeight = ALL_PEOPLE
                .stream()
                .collect(maxBy(weightComparator))
                .orElse(null);

        Person expectedPersonWithHighestWeight = TOM_BERG;

        Assert.assertEquals(expectedPersonWithHighestWeight, personWithHighestWeight);
    }

    @Test
    public void collectorsSummarizingDouble() {
        DoubleSummaryStatistics statistics = ALL_PEOPLE
                .stream()
                .collect(summarizingDouble(Person::getWeight));

        double expectedMaxWeight = TOM_BERG.getWeight();
        double expectedMinWeight = JULIA_CLARK.getWeight();
        double expectedCount = ALL_PEOPLE.size();
        double expectedSum = toDoubleStream(ALL_PEOPLE, Person::getWeight).sum();
        double expectedAverage = toDoubleStream(ALL_PEOPLE, Person::getWeight).average().getAsDouble();

        Assert.assertEquals(expectedMaxWeight, statistics.getMax(), DELTA);
        Assert.assertEquals(expectedMinWeight, statistics.getMin(), DELTA);
        Assert.assertEquals(expectedCount, statistics.getCount(), DELTA);
        Assert.assertEquals(expectedSum, statistics.getSum(), DELTA);
        Assert.assertEquals(expectedAverage, statistics.getAverage(), DELTA);
    }

    @Test
    public void collectorsJoining() {
        String peopleNames = ALL_PEOPLE
                .stream()
                .map(Person::getName)
                .distinct()
                .collect(joining("; "));

        String expectedPeopleNames = "Tom; Megan; Julia";

        Assert.assertEquals(expectedPeopleNames, peopleNames);
    }

    private <T> DoubleStream toDoubleStream(Collection<T> collection, ToDoubleFunction<? super T> mapper) {
        return collection.stream().mapToDouble(mapper);
    }
}
