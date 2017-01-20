package io.github.rafalposwiata.java_essence.stream.operation;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static io.github.rafalposwiata.java_essence.data.People.ALL_PEOPLE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static java.util.stream.Collectors.toList;

/**
 * @author Rafał Poświata.
 */
public class IntermediateOperationTest {

    @Test
    public void map() {
        List<String> surnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .collect(toList());

        List<String> expectedSurnames = Arrays.asList("Lee", "Berg", "Clark", "Clark");

        Assert.assertEquals(expectedSurnames, surnames);
    }

    @Test
    public void distinct() {
        List<String> distinctSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .distinct()
                .collect(toList());

        List<String> expectedDistinctSurnames = Arrays.asList("Lee", "Berg", "Clark");

        Assert.assertEquals(expectedDistinctSurnames, distinctSurnames);
    }

    @Test
    public void filter() {
        Predicate<Person> isMan = p -> MALE.equals(p.getGender());
        List<String> surnamesOfMen = ALL_PEOPLE
                .stream()
                .filter(isMan)
                .map(Person::getSurname)
                .collect(toList());

        List<String> expectedSurnamesOfMen = Arrays.asList("Lee", "Berg");

        Assert.assertEquals(expectedSurnamesOfMen, surnamesOfMen);
    }

    @Test
    public void limit() {
        long limitOfSurnames = 2;
        List<String> limitedSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .limit(limitOfSurnames)
                .collect(toList());

        List<String> expectedLimitedSurnames = Arrays.asList("Lee", "Berg");

        Assert.assertEquals(expectedLimitedSurnames, limitedSurnames);
    }

    @Test
    public void sorted() {
        List<String> sortedSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .sorted()
                .collect(toList());

        List<String> expectedSortedSurnames = Arrays.asList("Berg", "Clark", "Clark", "Lee");

        Assert.assertEquals(expectedSortedSurnames, sortedSurnames);
    }
}
