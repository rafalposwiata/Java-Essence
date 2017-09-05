package io.github.rafalposwiata.java_essence.design_pattern.observer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Rafał Poświata.
 */
public class Tweeter implements Observable<String> {

    private List<Observer<String>> observers = Lists.newArrayList();

    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String notification) {
        observers.forEach(observer -> observer.notify(notification));
    }
}
