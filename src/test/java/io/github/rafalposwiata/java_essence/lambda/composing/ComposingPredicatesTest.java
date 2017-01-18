package io.github.rafalposwiata.java_essence.lambda.composing;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static io.github.rafalposwiata.java_essence.data.People.ALL_PEOPLE;
import static io.github.rafalposwiata.java_essence.data.People.TOM_LEE;
import static java.util.stream.Collectors.toList;

/**
 * @author Rafał Poświata.
 */
public class ComposingPredicatesTest {

    @Test
    public void chainingPredicates() {
        Predicate<Person> isTom = person -> "Tom".equalsIgnoreCase(person.getName());
        Predicate<Person> isSlim = person -> person.getWeight() < 70;

        List<Person> selectedPeople = ALL_PEOPLE
                .stream()
                .filter(isTom
                        .and(isSlim))
                .collect(toList());

        List<Person> expectedSelectedPeople = Collections.singletonList(TOM_LEE);

        Assert.assertEquals(expectedSelectedPeople, selectedPeople);
    }
}
