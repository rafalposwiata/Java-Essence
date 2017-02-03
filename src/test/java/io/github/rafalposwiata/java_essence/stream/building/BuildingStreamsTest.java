package io.github.rafalposwiata.java_essence.stream.building;

import io.github.rafalposwiata.java_essence.model.Person;
import io.github.rafalposwiata.java_essence.utils.PathUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.rafalposwiata.java_essence.data.People.*;

/**
 * @author Rafał Poświata.
 */
public class BuildingStreamsTest {

    @Test
    public void streamFromValues() {
        List<Person> people = Stream
                .of(TOM_LEE, TOM_BERG, MEGAN_CLARK, JULIA_CLARK)
                .collect(Collectors.toList());

        List<Person> expectedPeople = ALL_PEOPLE;

        Assert.assertEquals(expectedPeople, people);
    }

    @Test
    public void streamFromFile() throws IOException {
        Path path = PathUtils.getPath(this, "TheLordOfTheRingsPoem");

        long wordRingOccurrence = Files
                .lines(path)
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .filter("Ring"::equalsIgnoreCase)
                .count();

        long expectedWordRingOccurrence = 3;

        Assert.assertEquals(expectedWordRingOccurrence, wordRingOccurrence);
    }
}
