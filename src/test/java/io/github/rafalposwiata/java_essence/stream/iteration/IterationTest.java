package io.github.rafalposwiata.java_essence.stream.iteration;

import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @author Rafał Poświata.
 */
public class IterationTest {

    @Test
    public void externalVsInternal() {
        List<Person> listOfMenExternal = listOfMenFromExternalIteration();
        List<Person> listOfMenInternal = listOfMenFromInternalIteration();

        List<Person> expectedListOfMen = Arrays.asList(TOM_LEE, TOM_BERG);

        assertEquals(expectedListOfMen, listOfMenExternal);
        assertEquals(expectedListOfMen, listOfMenInternal);
    }

    private List<Person> listOfMenFromExternalIteration() {
        List<Person> listOfMen = new ArrayList<>();
        for (Person person : ALL_PEOPLE) {
            if (MALE.equals(person.getGender()))
                listOfMen.add(person);
        }
        return listOfMen;
    }

    private List<Person> listOfMenFromInternalIteration() {
        return ALL_PEOPLE
                .stream()
                .filter(person -> MALE.equals(person.getGender()))
                .collect(toList());
    }
}
