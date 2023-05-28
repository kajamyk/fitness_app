package com.example.fitness_portal.core.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FatPercentageCalculatorTest {

    @Test
    void fatPercentageShouldBeHigherForWiderWaist() {
        FatPercentageCalculator fatPercentageCalculator = new FatPercentageCalculator();

        double result = fatPercentageCalculator.calculateFatPercentage(true, 170, 40, 90, 70);
        double result2 = fatPercentageCalculator.calculateFatPercentage(true, 170, 40, 90, 80);

        assertTrue(result < result2);
    }

    @Test
    void fatPercentageShouldBeHigherForFemales() {
        FatPercentageCalculator fatPercentageCalculator = new FatPercentageCalculator();

        double result = fatPercentageCalculator.calculateFatPercentage(true, 170, 40, 90, 70);
        double result2 = fatPercentageCalculator.calculateFatPercentage(false, 170, 40, 90, 70);

        assertTrue(result < result2);
    }

}