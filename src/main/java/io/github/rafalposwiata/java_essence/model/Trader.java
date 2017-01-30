package io.github.rafalposwiata.java_essence.model;

/**
 * @author Rafał Poświata.
 */
public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return String.format("Trader: %s in %s", name, city);
    }
}
