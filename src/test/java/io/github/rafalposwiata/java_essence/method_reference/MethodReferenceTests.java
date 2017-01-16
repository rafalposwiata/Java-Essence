package io.github.rafalposwiata.java_essence.method_reference;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Rafał Poświata.
 */
public class MethodReferenceTests {

    private Integer[] integers = {7, 33, 555};
    private String[] stringRepresentationsOfIntegers = {"7", "33", "555"};

    @Test
    public void staticMethod() {
        String[] stringsFromIntegers = Arrays
                .stream(integers)
                .map(String::valueOf) //static method String.valueOf(Object obj)
                .toArray(String[]::new);

        Assert.assertArrayEquals(stringRepresentationsOfIntegers, stringsFromIntegers);
    }

    @Test
    public void instanceMethodOfType() {
        Integer[] lengthsOfStrings = Arrays
                .stream(stringRepresentationsOfIntegers)
                .map(String::length) //instance method stringObj.length()
                .toArray(Integer[]::new);
        Integer[] expectedLengthsOfStrings = {1, 2, 3};

        Assert.assertArrayEquals(expectedLengthsOfStrings, lengthsOfStrings);
    }

    @Test
    public void instanceMethodOfObject() {
        StringFormatter stringFormatter = new StringFormatter();
        String[] stringsWithStarSuffixes = Arrays
                .stream(stringRepresentationsOfIntegers)
                .map(stringFormatter::addStarSuffix) //instance method of object stringFormatter
                .toArray(String[]::new);
        String[] expectedStringsWithStarSuffixes = {"7*", "33*", "555*"};

        Assert.assertArrayEquals(expectedStringsWithStarSuffixes, stringsWithStarSuffixes);
    }

    private class StringFormatter {

        public String addStarSuffix(String value) {
            return addSuffix(value, "*");
        }

        public String addSuffix(String value, String suffix) {
            return value + suffix;
        }
    }
}
