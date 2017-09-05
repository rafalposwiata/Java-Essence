package io.github.rafalposwiata.java_essence.lambda.example;

import com.google.common.collect.Lists;
import io.github.rafalposwiata.java_essence.design_pattern.observer.Tweeter;
import io.github.rafalposwiata.java_essence.design_pattern.strategy.Validator;
import io.github.rafalposwiata.java_essence.design_pattern.template_method.Extractor;
import org.junit.Test;

import java.util.List;

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

    /**
     * The observer design pattern is a common solution when an object (called the observable) needs to automatically
     * notify a list of other objects (called observers) when some event happens (for example, a state change).
     */
    @Test
    public void observerPattern() {
        List<String> sportTweets = Lists.newArrayList();
        List<String> scienceTweets = Lists.newArrayList();
        List<String> businessTweets = Lists.newArrayList();

        Tweeter tweeter = new Tweeter();
        tweeter.registerObserver((String notification) -> {
            if (notification.contains("football")) sportTweets.add(notification);
        });
        tweeter.registerObserver((String notification) -> {
            if (notification.contains("medicine")) scienceTweets.add(notification);
        });
        tweeter.registerObserver((String notification) -> {
            if (notification.contains("money")) businessTweets.add(notification);
        });

        tweeter.notifyObservers("PSG spend the most money for contracts then any other football club.");
        assertEquals(1, sportTweets.size());
        assertEquals(0, scienceTweets.size());
        assertEquals(1, businessTweets.size());

        tweeter.notifyObservers("medicine need more money to develop.");
        assertEquals(1, sportTweets.size());
        assertEquals(1, scienceTweets.size());
        assertEquals(2, businessTweets.size());
    }
}
