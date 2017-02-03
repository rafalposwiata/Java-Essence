package io.github.rafalposwiata.java_essence.model;

import java.util.Objects;

/**
 * @author Rafał Poświata.
 */
public class PythagoreanTriple {
    double a;
    double b;
    double c;

    public PythagoreanTriple(double a, double b) {
        this(a, b, Math.sqrt(a * a + b * b));
    }

    public PythagoreanTriple(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isValid() {
        return c % 1 == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PythagoreanTriple)) return false;

        PythagoreanTriple other = (PythagoreanTriple) obj;

        return Double.compare(other.a, a) == 0
                && Double.compare(other.b, b) == 0
                && Double.compare(other.c, c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return String.format("[%.0f, %.0f, %.0f]", a, b, c);
    }
}
