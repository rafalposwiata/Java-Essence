package io.github.rafalposwiata.java_essence.data;

import com.google.common.collect.ImmutableList;
import io.github.rafalposwiata.java_essence.model.Person;

import java.util.List;

/**
 * @author Rafał Poświata.
 */
public class People {

    private People(){}

    public static final Person TOM_LEE = new Person("Tom", "Lee", 75.3);

    public static final Person TOM_BERG = new Person("Tom", "Berg", 90.6);

    public static final Person MEGAN_CLARK = new Person("Megan", "Clark", 53.6);

    public static final Person JULIA_CLARK = new Person("Julia", "Clark", 52.8);

    public static final List<Person> ALL_PEOPLE = ImmutableList.of(TOM_LEE, TOM_BERG, MEGAN_CLARK, JULIA_CLARK);
}
