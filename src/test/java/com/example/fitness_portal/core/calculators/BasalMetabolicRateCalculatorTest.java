package com.example.fitness_portal.core.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasalMetabolicRateCalculatorTest {

    @Test
    void basalMetabolicRateShouldBeHigherForActivePerson() {
        BasalMetabolicRateCalculator basalMetabolicRateCalculator = new BasalMetabolicRateCalculator();

        double result = basalMetabolicRateCalculator.calculateBasalMetabolicRate(true, 100, 170, 21, 1);
        double result2 = basalMetabolicRateCalculator.calculateBasalMetabolicRate(true, 100, 170, 21, 10);

        assertTrue(result2 > result);
    }

    @Test
    void basalMetabolicRateShouldBeHigherForMale() {
        BasalMetabolicRateCalculator basalMetabolicRateCalculator = new BasalMetabolicRateCalculator();

        double result = basalMetabolicRateCalculator.calculateBasalMetabolicRate(true, 70, 170, 21, 10);
        double result2 = basalMetabolicRateCalculator.calculateBasalMetabolicRate(false, 70, 170, 21, 10);

        assertTrue(result > result2);
    }

}