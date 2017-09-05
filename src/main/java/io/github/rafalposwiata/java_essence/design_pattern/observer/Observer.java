package io.github.rafalposwiata.java_essence.design_pattern.observer;

/**
 * @author Rafał Poświata.
 */
public interface Observer<T> {

    void notify(T notification);
}
