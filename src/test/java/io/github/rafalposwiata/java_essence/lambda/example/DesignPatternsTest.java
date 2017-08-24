package io.github.rafalposwiata.java_essence.lambda.example;

import io.github.rafalposwiata.java_essence.design_pattern.strategy.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
