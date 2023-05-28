package com.example.fitness_portal.core.calculators;

import javax.validation.constraints.Min;

public class FatPercentageCalculator {

    public double calculateFatPercentage(boolean isMale, @Min(1) double height, @Min(1) double neckSize,
                                         @Min(1) double hipSize, @Min(1) double waistSize) {
        if (isMale) {
            return 86.01 * Math.log10(waistSize - neckSize) - 70.041 * Math.log10(height) + 36.76;
        }
        return 163.205 * Math.log10(waistSize + hipSize - neckSize) - 97.684 * Math.log10(height) - 78.387;
    }
}
