package com.example.fitness_portal.core.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexCalculatorTest {

    @Test
    void shouldReturnNormalWeight() {
        int age = 20;
        double weight = 60;
        int height = 170;
        boolean isMale = false;
        BodyMassIndexCalculator bodyMassIndexCalculator = new BodyMassIndexCalculator();

        String result = bodyMassIndexCalculator.getBmiResultText(age, weight, height, isMale);

        assertEquals(result, "Prawidłowa waga");
    }

    @Test
    void shouldReturnUnderweight() {
        int age = 40;
        double weight = 65;
        int height = 200;
        boolean isMale = true;
        BodyMassIndexCalculator bodyMassIndexCalculator = new BodyMassIndexCalculator();

        String result = bodyMassIndexCalculator.getBmiResultText(age, weight, height, isMale);

        assertEquals(result, "Niedowaga");
    }

    @Test
    void ShouldReturnObese() {
        int age = 70;
        double weight = 120;
        int height = 150;
        boolean isMale = true;
        BodyMassIndexCalculator bodyMassIndexCalculator = new BodyMassIndexCalculator();

        String result = bodyMassIndexCalculator.getBmiResultText(age, weight, height, isMale);

        assertEquals(result, "Otyłość");
    }
}