package io.github.rafalposwiata.java_essence.stream.operation;

import com.google.common.collect.Sets;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static io.github.rafalposwiata.java_essence.data.People.ALL_PEOPLE;
import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static java.util.stream.Collectors.toSet;

/**
 * @author Rafał Poświata.
 */
public class TerminalOperationTest {

    @Test
    public void forEach() {
        ALL_PEOPLE
                .stream()
                .forEach(person -> Assert.assertTrue(person.getWeight() > 0));
    }

    @Test
    public void count() {
        long numberOfWomen = ALL_PEOPLE
                .stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .count();

        long expectedNumberOfWoman = 2;

        Assert.assertEquals(expectedNumberOfWoman, numberOfWomen);
    }

    @Test
    public void collect() {
        Set<String> names = ALL_PEOPLE
                .stream()
                .map(Person::getName)
                .collect(toSet());

        Set<String> expectedNames = Sets.newHashSet("Tom", "Megan", "Julia");

        Assert.assertEquals(expectedNames, names);
    }
}
