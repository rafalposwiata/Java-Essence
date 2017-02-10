package io.github.rafalposwiata.java_essence.stream.building;

import io.github.rafalposwiata.java_essence.mock.RandomMock;
import io.github.rafalposwiata.java_essence.model.Person;
import io.github.rafalposwiata.java_essence.utils.PathUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.github.rafalposwiata.java_essence.data.People.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author Rafał Poświata.
 */
public class BuildingStreamsTest {

    @Test
    public void streamFromValues() {
        List<Person> people = Stream
                .of(TOM_LEE, TOM_BERG, MEGAN_CLARK, JULIA_CLARK)
                .collect(toList());

        List<Person> expectedPeople = ALL_PEOPLE;

        Assert.assertEquals(expectedPeople, people);
    }

    @Test
    public void streamFromFile() throws IOException {
        Path pathToFile = PathUtils.getPath(this, "TheLordOfTheRingsPoem");

        long wordRingOccurrence = Files
                .lines(pathToFile)
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .filter("Ring"::equalsIgnoreCase)
                .count();

        long expectedWordRingOccurrence = 3;

        Assert.assertEquals(expectedWordRingOccurrence, wordRingOccurrence);
    }

    @Test
    public void streamFromFunctionIterate() {
        List<Integer> evenNumbers = Stream
                .iterate(0, n -> n + 2)
                .limit(5)
                .collect(toList());

        List<Integer> expectedEvenNumbers = asList(0, 2, 4, 6, 8);

        Assert.assertEquals(expectedEvenNumbers, evenNumbers);
    }

    @Test
    public void streamFromFunctionGenerate() {
        List<Boolean> expectedBooleanValues = asList(true, true, false, true, false);

        Supplier<Boolean> randomBoolean = new RandomMock<>(expectedBooleanValues);

        List<Boolean> booleanValues = Stream
                .generate(randomBoolean)
                .limit(5)
                .collect(toList());

        Assert.assertEquals(expectedBooleanValues, booleanValues);
    }
}
