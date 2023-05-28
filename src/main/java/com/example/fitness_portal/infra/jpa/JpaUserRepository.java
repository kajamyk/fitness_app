package com.example.fitness_portal.infra.jpa;


import com.example.fitness_portal.core.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<AppUser, Long> {

}
