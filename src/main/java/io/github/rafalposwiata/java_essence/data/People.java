package io.github.rafalposwiata.java_essence.data;

import com.google.common.collect.ImmutableList;
import io.github.rafalposwiata.java_essence.model.Person;

import java.util.List;

import static io.github.rafalposwiata.java_essence.model.Gender.FEMALE;
import static io.github.rafalposwiata.java_essence.model.Gender.MALE;

/**
 * @author Rafał Poświata.
 */
public class People {

    private People(){}

    public static final Person TOM_LEE = new Person("Tom", "Lee", MALE, 65.3);

    public static final Person TOM_BERG = new Person("Tom", "Berg", MALE, 100.6);

    public static final Person MEGAN_CLARK = new Person("Megan", "Clark", FEMALE, 53.6);

    public static final Person JULIA_CLARK = new Person("Julia", "Clark", FEMALE, 52.8);

    public static final List<Person> ALL_PEOPLE = ImmutableList.of(TOM_LEE, TOM_BERG, MEGAN_CLARK, JULIA_CLARK);
}
