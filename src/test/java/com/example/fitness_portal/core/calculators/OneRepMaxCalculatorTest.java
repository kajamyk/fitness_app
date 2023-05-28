package com.example.fitness_portal.core.calculators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OneRepMaxCalculatorTest {

    @Test
    void ORMShouldBeHigherForMoreReps() {
        OneRepMaxCalculator oneRepMaxCalculator = new OneRepMaxCalculator();

        double result = oneRepMaxCalculator.calculateOneRepMax(10, 100);
        double result2 = oneRepMaxCalculator.calculateOneRepMax(15, 100);

        assertTrue(result < result2);
    }

    @Test
    void ORMShouldBeHigherForMoreWeight() {
        OneRepMaxCalculator oneRepMaxCalculator = new OneRepMaxCalculator();

        double result = oneRepMaxCalculator.calculateOneRepMax(10, 100);
        double result2 = oneRepMaxCalculator.calculateOneRepMax(10, 150);

        assertTrue(result < result2);
    }
}