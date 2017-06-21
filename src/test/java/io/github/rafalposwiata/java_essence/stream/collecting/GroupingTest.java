package io.github.rafalposwiata.java_essence.stream.collecting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.github.rafalposwiata.java_essence.model.Gender;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static io.github.rafalposwiata.java_essence.utils.TestUtils.containsSameElements;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rafał Poświata.
 */
public class GroupingTest {

    @Test
    public void collectorsGroupingByWithMethodReference() {
        Map<Gender, List<Person>> peopleGroupedByGender = ALL_PEOPLE
                .stream()
                .collect(groupingBy(Person::getGender));

        Map<Gender, List<Person>> expectedPeopleGroupedByGender = ImmutableMap.of(
                MALE, Lists.newArrayList(TOM_BERG, TOM_LEE),
                FEMALE, Lists.newArrayList(MEGAN_CLARK, JULIA_CLARK)
        );

        assertEquals(expectedPeopleGroupedByGender.keySet(), peopleGroupedByGender.keySet());
        assertTrue(containsSameElements(expectedPeopleGroupedByGender.get(MALE), peopleGroupedByGender.get(MALE)));
        assertTrue(containsSameElements(expectedPeopleGroupedByGender.get(FEMALE), peopleGroupedByGender.get(FEMALE)));
    }
}
