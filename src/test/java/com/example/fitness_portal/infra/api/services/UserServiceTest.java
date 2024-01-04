package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.infra.jpa.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    JpaUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUserTest() throws Exception {

        String userName = "user123";
        String password = "Password.123";
        String gender = "female";
        Date date = new Date();
        String encodedPassword = "EncodedPassword";

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        userService.addUserNew(userName, password, gender, date);

        verify(userRepository, times(1)).save(any());
    }

}