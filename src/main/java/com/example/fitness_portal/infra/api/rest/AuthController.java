package com.example.fitness_portal.infra.api.rest;

import com.example.fitness_portal.core.AuthRequest;
import com.example.fitness_portal.infra.api.security.JwtUtil;
import com.example.fitness_portal.infra.api.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        try {
            if(!userService.isUserValid(authRequest)) {
                throw new Exception("Invalid auth data");
            }
            String token = jwtUtil.generateToken(authRequest.getLogin());
            Map<String, String> response = new HashMap<>();
            response.put("token", "Bearer " + token);
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        try {
            if (registerRequest.confirmPassword.equals(registerRequest.password)) {
                userService.addUserNew(registerRequest.login, registerRequest.password, registerRequest.gender, registerRequest.birthDate);
                return ResponseEntity.ok(new ArrayList<>());
            }
        }
        catch (Exception ignored) {

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Data
    private static class RegisterRequest {
        String login;
        String password;
        String confirmPassword;
        String gender;
        Date birthDate;
    }
}
