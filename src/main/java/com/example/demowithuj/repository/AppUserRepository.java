package com.example.demowithuj.repository;

import com.example.demowithuj.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findById(Long Id);

    Optional<AppUser> findByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);
}
