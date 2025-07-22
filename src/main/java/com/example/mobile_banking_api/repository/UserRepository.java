package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
