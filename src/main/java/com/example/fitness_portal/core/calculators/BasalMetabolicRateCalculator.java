package com.example.fitness_portal.core.calculators;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class BasalMetabolicRateCalculator {

    public double calculateBasalMetabolicRate(boolean isMale, @Min(1) double weight,
                                              @Min(1) double height, @Min(1) int age, @Min(1) @Max(10) double activityLevel) {
        if (isMale) {
            return (88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age)) * (activityLevel / 10 + 1.2);
        }
        return (447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age)) * (activityLevel / 10 + 1.2);
    }
}
