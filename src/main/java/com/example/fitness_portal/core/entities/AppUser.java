package com.example.fitness_portal.core.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity(name = "app_user")
public class AppUser {
    @NotNull
    boolean isMale;
    @NotNull
    Date birthDate;
    @Id
    @Column(name = "app_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    @Column(unique = true)
    @Length(min = 4, max = 20)
    private String userName;
    @NotNull
    @Length(min = 8, max = 100)
    private String userPassword;


    public AppUser() {

    }

    public AppUser(String userName, String userPassword, Date birthDate, boolean isMale) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isMale = isMale;
        this.birthDate = birthDate;
    }
}
