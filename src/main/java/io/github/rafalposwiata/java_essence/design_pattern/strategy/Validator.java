package io.github.rafalposwiata.java_essence.design_pattern.strategy;

/**
 * @author Rafał Poświata.
 */
public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
