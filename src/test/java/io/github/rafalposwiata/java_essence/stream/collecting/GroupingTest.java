package io.github.rafalposwiata.java_essence.stream.collecting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.github.rafalposwiata.java_essence.model.BMIGroup;
import io.github.rafalposwiata.java_essence.model.Gender;
import io.github.rafalposwiata.java_essence.model.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static io.github.rafalposwiata.java_essence.model.BMIGroup.*;
import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;
import static io.github.rafalposwiata.java_essence.utils.TestUtils.assertSameElements;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

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
        assertSameElements(expectedPeopleGroupedByGender.get(MALE), peopleGroupedByGender.get(MALE));
        assertSameElements(expectedPeopleGroupedByGender.get(FEMALE), peopleGroupedByGender.get(FEMALE));
    }

    @Test
    public void collectorsMultilevelGroupingBy() {
        Function<Person, BMIGroup> bmiClassifier = person -> BMIGroup.getGroup(person.getWeight(), person.getHeight());

        Map<BMIGroup, Map<Gender, List<Person>>> peopleGroupedByBMIAndGender = ALL_PEOPLE
                .stream()
                .collect(groupingBy(bmiClassifier, groupingBy(Person::getGender)));

        List<Person> expectedObeseMale = Lists.newArrayList(TOM_BERG);
        List<Person> expectedUnderweightFemale = Lists.newArrayList(MEGAN_CLARK);
        List<Person> expectedNormalMale = Lists.newArrayList(TOM_LEE);
        List<Person> expectedNormalFemale = Lists.newArrayList(JULIA_CLARK);

        assertSameElements(expectedObeseMale, peopleGroupedByBMIAndGender.get(OBESE).get(MALE));
        assertSameElements(expectedUnderweightFemale, peopleGroupedByBMIAndGender.get(UNDERWEIGHT).get(FEMALE));
        assertSameElements(expectedNormalMale, peopleGroupedByBMIAndGender.get(NORMAL).get(MALE));
        assertSameElements(expectedNormalFemale, peopleGroupedByBMIAndGender.get(NORMAL).get(FEMALE));
    }

    @Test
    public void collectingResultInEachSubgroup() {
        Collector<Person, ?, Optional<Person>> highestPerson = maxBy(Comparator.comparingDouble(Person::getHeight));

        Map<Gender, Person> highestPersonInEachGender = ALL_PEOPLE
                .stream()
                .collect(groupingBy(Person::getGender, collectingAndThen(highestPerson, Optional::get)));

        Person expectedHighestMale = TOM_BERG;
        Person expectedHighestFemale = MEGAN_CLARK;

        assertEquals(expectedHighestMale, highestPersonInEachGender.get(MALE));
        assertEquals(expectedHighestFemale, highestPersonInEachGender.get(FEMALE));
    }
}
