package com.example.fitness_portal.core.calculators;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BodyMassIndexCalculator {

    private final List<AgeFactor> ageFactorListForFemales = new ArrayList<>(
            Arrays.asList(
                    new AgeFactor(18, 1),
                    new AgeFactor(34, 0.95),
                    new AgeFactor(54, 0.9),
                    new AgeFactor(64, 0.85),
                    new AgeFactor(125, 0.80)
            )
    );

    private final List<AgeFactor> ageFactorListForMales = new ArrayList<>(
            Arrays.asList(
                    new AgeFactor(18, 1),
                    new AgeFactor(24, 1.1),
                    new AgeFactor(34, 1),
                    new AgeFactor(54, 0.9),
                    new AgeFactor(64, 0.85),
                    new AgeFactor(125, 0.80)
            )
    );

    private final List<BmiInterpreter> bmiInterpreterList = new ArrayList<>(
            Arrays.asList(
                    new BmiInterpreter(18.5, "Niedowaga"),
                    new BmiInterpreter(24.5, "Prawidłowa waga"),
                    new BmiInterpreter(29.9, "Nadwaga"),
                    new BmiInterpreter(110, "Otyłość")
            )
    );

    private double getBmiResult(int age, double weight, double height, boolean isMale) {
        height /= 100;
        double bmi = weight / (height * height);

        if (isMale) {
            double factor = ageFactorListForMales
                    .stream()
                    .filter(ageFactor -> ageFactor.ageLimit >= age)
                    .findFirst()
                    .orElseThrow()
                    .factor;
            return bmi * factor;
        }

        double factor = ageFactorListForFemales
                .stream()
                .filter(ageFactor -> ageFactor.ageLimit >= age)
                .findFirst()
                .orElseThrow()
                .factor;
        return bmi * factor;
    }

    public String getBmiResultText(@Min(1) int age, @Min(1) double weight, @Min(1) double height, boolean isMale) {
        double result = getBmiResult(age, weight, height, isMale);
        return bmiInterpreterList.stream()
                .filter(bmiInterpreter -> bmiInterpreter.bmiLimit >= result)
                .findFirst()
                .orElseThrow()
                .bmiDescription;
    }

    @AllArgsConstructor
    private static class AgeFactor {
        private int ageLimit;
        private double factor;
    }

    @AllArgsConstructor
    private static class BmiInterpreter {
        private double bmiLimit;
        private String bmiDescription;
    }
}
