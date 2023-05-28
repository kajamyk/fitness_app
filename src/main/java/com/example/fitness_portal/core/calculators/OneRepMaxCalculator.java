package com.example.fitness_portal.core.calculators;

import javax.validation.constraints.Min;

public class OneRepMaxCalculator {

    public double calculateOneRepMax(@Min(1) int numberOfReps, @Min(0) double weight) {
        return weight / (1.0278 - (0.0278 * numberOfReps));
    }
}
