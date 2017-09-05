package io.github.rafalposwiata.java_essence.design_pattern.observer;

/**
 * @author Rafał Poświata.
 */
public interface Observable<T> {

    void registerObserver(Observer<T> observer);

    void notifyObservers(T notification);
}
