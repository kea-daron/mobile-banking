package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Integer> {
}
