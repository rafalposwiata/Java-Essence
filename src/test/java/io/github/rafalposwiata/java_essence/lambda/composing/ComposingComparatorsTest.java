package io.github.rafalposwiata.java_essence.lambda.composing;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @author Rafał Poświata.
 */
public class ComposingComparatorsTest {

    @Test
    public void chainingComparators() {
        List<Person> peopleSortedBySurnameAndNameReverted = ALL_PEOPLE
                .stream()
                .sorted(comparing(Person::getSurname)
                        .thenComparing(Person::getName)
                        .reversed()
                )
                .collect(toList());

        List<Person> expectedOrderOfPeople = Arrays.asList(TOM_LEE, MEGAN_CLARK, JULIA_CLARK, TOM_BERG);

        assertEquals(expectedOrderOfPeople, peopleSortedBySurnameAndNameReverted);
    }
}
