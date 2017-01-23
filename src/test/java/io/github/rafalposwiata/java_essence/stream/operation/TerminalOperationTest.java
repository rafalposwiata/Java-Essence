package io.github.rafalposwiata.java_essence.stream.operation;

import com.google.common.collect.Sets;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static java.util.Arrays.asList;
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

    @Test
    public void anyMatch() {
        boolean containsMan = ALL_PEOPLE
                .stream()
                .anyMatch(person -> MALE.equals(person.getGender()));

        Assert.assertTrue(containsMan);
    }

    @Test
    public void allMatch() {
        boolean everyoneIsLighterThan100kg = ALL_PEOPLE
                .stream()
                .allMatch(person -> person.getWeight() < 100);

        Assert.assertTrue(everyoneIsLighterThan100kg);
    }

    @Test
    public void noneMatch() {
        boolean nobodyIsHeavierThan100kg = ALL_PEOPLE
                .stream()
                .noneMatch(person -> person.getWeight() > 100);

        Assert.assertTrue(nobodyIsHeavierThan100kg);
    }

    @Test
    public void findAny() {
        Person anyWoman = ALL_PEOPLE
                .stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .findAny()
                .orElse(null);

        Assert.assertNotNull(anyWoman);
    }

    @Test
    public void findFirst() {
        Person firstWoman = ALL_PEOPLE
                .stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(firstWoman);
    }

    @Test
    public void findAnyVsFindFirst() {
        Person anyWoman = ALL_PEOPLE
                .parallelStream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .findAny()
                .orElse(null);

        Person firstWomen = ALL_PEOPLE
                .parallelStream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .findFirst()
                .orElse(null);

        // With findAny we don't know which one will be chosen
        Assert.assertTrue(MEGAN_CLARK.equals(anyWoman) || JULIA_CLARK.equals(anyWoman));

        // With findFirst will be chosen Megan, because see is first woman in ALL_PEOPLE list
        Assert.assertTrue(MEGAN_CLARK.equals(firstWomen));
    }

    @Test
    public void reduce() {
        Integer sum = asList(1, 2, 3, 4, 5)
                .stream()
                .reduce(Integer::sum)
                .orElse(0);

        Integer expectedSum = 15;

        Assert.assertEquals(expectedSum, sum);
    }

    @Test
    public void reduceWithInitialValue() {
        Integer sum = asList(1, 2, 3, 4, 5)
                .stream()
                .reduce(100, Integer::sum);

        Integer expectedSum = 115;

        Assert.assertEquals(expectedSum, sum);
    }
}
