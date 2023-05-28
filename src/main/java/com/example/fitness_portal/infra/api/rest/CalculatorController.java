package com.example.fitness_portal.infra.api.rest;

import com.example.fitness_portal.core.calculators.BasalMetabolicRateCalculator;
import com.example.fitness_portal.core.calculators.OneRepMaxCalculator;
import com.example.fitness_portal.infra.api.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calculators")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class CalculatorController {
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/bmi")
    public ResponseEntity getBmiResult(@RequestBody BMIBody bmiBody) {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("bmi", userService.getUserBmiResult(bmiBody));
            return ResponseEntity.ok(response);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/orm")
    public double getORMResult(@RequestBody ORMBody ormBody) {
        OneRepMaxCalculator oneRepMaxCalculator = new OneRepMaxCalculator();
        return oneRepMaxCalculator.calculateOneRepMax(ormBody.numberOfReps, ormBody.weight);
    }

    @PostMapping("/bmr")
    public double getBMRResult(@RequestBody BMRBody bmrBody) {
        try {
            return userService.calculateUserBasalMetabolicRate(bmrBody);
        } catch (Exception exception) {
            return 0;
        }
    }

    @PostMapping("/bfp")
    public double getFatPercentageResult(@RequestBody FPBody fpBody) {
        try {
            return userService.calculateUserFatPercentage(fpBody);
        } catch (Exception exception) {
            return 0;
        }
    }

    @Data
    public static class ORMBody {
        double weight;
        int numberOfReps;
    }

    @Data
    public static class BMRBody {
        double weight;
        double height;
        int activityLevel;
    }

    @Data
    public static class FPBody {
        double neckSize;
        double hipSize;
        double waistSize;
        double height;
    }

    @Data
    public static class BMIBody {
        double weight;
        double height;
    }
}
