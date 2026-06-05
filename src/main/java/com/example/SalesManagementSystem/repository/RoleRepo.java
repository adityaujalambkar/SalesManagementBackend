package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.model.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepo extends JpaRepository<Role,UUID> {
  
}
