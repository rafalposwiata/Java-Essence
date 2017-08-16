package io.github.rafalposwiata.java_essence.model;

import java.util.Objects;

/**
 * @author Rafał Poświata.
 */
public class Person {

    private String name;
    private String surname;
    private Gender gender;
    private double weight;
    private double height;

    public Person(String name, String surname, Gender gender, double weight, double height) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;

        Person person = (Person) obj;
        return name.equals(person.name)
                && surname.equals(person.surname)
                && gender.equals(person.gender)
                && Double.compare(weight, person.weight) == 0
                && Double.compare(height, person.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, weight, height);
    }

    @Override
    public String toString() {
        return String.format("{%s %s}", name, surname);
    }
}
