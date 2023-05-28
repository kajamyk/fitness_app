package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.AuthRequest;
import com.example.fitness_portal.core.calculators.BasalMetabolicRateCalculator;
import com.example.fitness_portal.core.calculators.BodyMassIndexCalculator;
import com.example.fitness_portal.core.calculators.CalculatorUtil;
import com.example.fitness_portal.core.calculators.FatPercentageCalculator;
import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.infra.api.rest.CalculatorController;
import com.example.fitness_portal.infra.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    JpaUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<AppUser> findUserByUserName(String username) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst();
    }

    public void addUserNew(String userName, String password, String gender, Date birthdate) throws Exception {
        if (isUserNameAvailable(userName)) {
            boolean isMale = !gender.equals("female");
            AppUser user = new AppUser(userName, passwordEncoder.encode(password), birthdate, isMale);
            userRepository.save(user);
        }
        else {
            throw new Exception("Username is already taken!");
        }
    }

    public boolean isUserNameAvailable(String userName) {
        return userRepository.findAll()
                .stream()
                .filter(appUser -> appUser.getUserName().equals(userName))
                .toList()
                .size() == 0;
    }

    public AppUser getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return findUserByUserName(userDetails.getUsername()).orElseThrow();
        } else {
            throw new Exception("No current user was found.");
        }
    }

    public double calculateUserBasalMetabolicRate(CalculatorController.BMRBody bmrBody) throws Exception {
        BasalMetabolicRateCalculator basalMetabolicRateCalculator = new BasalMetabolicRateCalculator();
        AppUser user = getCurrentUser();
        return basalMetabolicRateCalculator.calculateBasalMetabolicRate
                (user.isMale(), bmrBody.getWeight(), bmrBody.getHeight(), CalculatorUtil.calculateAge(user.getBirthDate()), bmrBody.getActivityLevel());
    }

    public String getUserBmiResult(CalculatorController.BMIBody bmiBody) throws Exception {
        BodyMassIndexCalculator bodyMassIndexCalculator = new BodyMassIndexCalculator();
        AppUser user = getCurrentUser();
        return bodyMassIndexCalculator.getBmiResultText
                (CalculatorUtil.calculateAge(user.getBirthDate()), bmiBody.getWeight(), bmiBody.getHeight(), user.isMale());
    }
    public double calculateUserFatPercentage(CalculatorController.FPBody fpBody) throws Exception {
        FatPercentageCalculator fatPercentageCalculator = new FatPercentageCalculator();
        AppUser user = getCurrentUser();
        return fatPercentageCalculator.calculateFatPercentage
                (user.isMale(), fpBody.getHeight(), fpBody.getNeckSize(), fpBody.getHipSize(), fpBody.getWaistSize());
    }

    public boolean isUserValid(AuthRequest authRequest) {
        AppUser user = findUserByUserName(authRequest.getLogin()).orElseThrow();
        return passwordEncoder.matches(authRequest.getPassword(), user.getUserPassword());
    }
}
