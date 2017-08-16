package io.github.rafalposwiata.java_essence.model;

/**
 * Underweight: BMI is less than 18.5
 * Normal weight: BMI is 18.5 to 24.9
 * Overweight: BMI is 25 to 29.9
 * Obese: BMI is 30 or more
 *
 * @author Rafał Poświata.
 */
public enum BMIGroup {
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    OBESE;

    public static BMIGroup getGroup(double weightInKilograms, double heightInMeters) {
        double bmi = weightInKilograms / (heightInMeters * heightInMeters);
        return getGroup(bmi);
    }

    public static BMIGroup getGroup(double bmi) {
        if (bmi < 18.5) return UNDERWEIGHT;
        else if (bmi < 24.9) return NORMAL;
        else if (bmi < 29.9) return OVERWEIGHT;
        return OBESE;
    }
}
