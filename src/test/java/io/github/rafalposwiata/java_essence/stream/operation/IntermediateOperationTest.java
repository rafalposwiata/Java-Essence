package io.github.rafalposwiata.java_essence.stream.operation;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static io.github.rafalposwiata.java_essence.data.People.ALL_PEOPLE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static java.util.Arrays.asList;
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

        List<String> expectedSurnames = asList("Lee", "Berg", "Clark", "Clark");

        Assert.assertEquals(expectedSurnames, surnames);
    }

    @Test
    public void flatMap() {
        List<String> namesAndSurnames = ALL_PEOPLE
                .stream()
                .flatMap(person -> asList(person.getName(), person.getSurname()).stream())
                .collect(toList());

        List<String> expectedNamesAndSurnames = asList("Tom", "Lee", "Tom", "Berg", "Megan", "Clark", "Julia", "Clark");

        Assert.assertEquals(expectedNamesAndSurnames, namesAndSurnames);
    }

    @Test
    public void flatMapNumberPairs() {
        List<Integer> numbers1 = asList(1, 2, 3);
        List<Integer> numbers2 = asList(3, 4);

        List<String> pairs = numbers1
                .stream()
                .flatMap(n1 -> numbers2
                        .stream()
                        .map(n2 -> n1 + "_" + n2))
                .collect(toList());

        List<String> expectedPairs = asList("1_3", "1_4", "2_3", "2_4", "3_3", "3_4");

        Assert.assertEquals(expectedPairs, pairs);
    }

    @Test
    public void distinct() {
        List<String> distinctSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .distinct()
                .collect(toList());

        List<String> expectedDistinctSurnames = asList("Lee", "Berg", "Clark");

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

        List<String> expectedSurnamesOfMen = asList("Lee", "Berg");

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

        List<String> expectedLimitedSurnames = asList("Lee", "Berg");

        Assert.assertEquals(expectedLimitedSurnames, limitedSurnames);
    }

    @Test
    public void skip() {
        long numberOfSurnamesToSkip = 2;
        List<String> notSkippedSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .skip(numberOfSurnamesToSkip)
                .collect(toList());

        List<String> expectedNotSkippedSurnames = asList("Clark", "Clark");

        Assert.assertEquals(expectedNotSkippedSurnames, notSkippedSurnames);
    }

    @Test
    public void sorted() {
        List<String> sortedSurnames = ALL_PEOPLE
                .stream()
                .map(Person::getSurname)
                .sorted()
                .collect(toList());

        List<String> expectedSortedSurnames = asList("Berg", "Clark", "Clark", "Lee");

        Assert.assertEquals(expectedSortedSurnames, sortedSurnames);
    }
}
