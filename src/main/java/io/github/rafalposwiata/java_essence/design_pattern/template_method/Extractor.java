package io.github.rafalposwiata.java_essence.design_pattern.template_method;

import java.util.function.Function;

/**
 * @author Rafał Poświata.
 */
public class Extractor {

    public String extractAndLog(String text, Function<String, String> extractMethod) {
        log("Input", text);
        String extractedText = extractMethod.apply(text);
        log("Output", extractedText);
        return extractedText;
    }

    private void log(String label, String text) {
        System.out.println(label + ": " + text);
    }
}
