package io.github.rafalposwiata.java_essence.lambda.example;

import io.github.rafalposwiata.java_essence.design_pattern.strategy.Validator;
import io.github.rafalposwiata.java_essence.design_pattern.template_method.Extractor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Rafał Poświata.
 */
public class DesignPatternsTest {

    /**
     * The strategy pattern is a common solution for representing
     * a family of algorithms and letting you choose among them at runtime.
     */
    @Test
    public void strategyPattern() {
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        assertTrue(numericValidator.validate("123"));
        assertFalse(numericValidator.validate("apple"));

        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        assertTrue(lowerCaseValidator.validate("apple"));
        assertFalse(lowerCaseValidator.validate("Apple"));
    }

    /**
     * The template method design pattern is a common solution when you need to represent
     * the outline of an algorithm and have the additional flexibility to change certain parts of it.
     */
    @Test
    public void templateMethodPattern() {
        String data = "Test 123";
        Extractor extractor = new Extractor();

        String extractedLetters = extractor.extractAndLog(data, d -> d.replaceAll("[\\d\\s]", ""));
        String expectedExtractedLetters = "Test";
        assertEquals(expectedExtractedLetters, extractedLetters);

        String extractedNumbers = extractor.extractAndLog(data, d -> d.replaceAll("[a-zA-Z\\s]+", ""));
        String expectedExtractedNumbers = "123";
        assertEquals(expectedExtractedNumbers, extractedNumbers);
    }
}
