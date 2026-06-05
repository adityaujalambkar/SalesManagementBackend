package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.model.Tenant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TenantRepo extends JpaRepository<Tenant,UUID> {

  Optional<Tenant> findByEmail(String email);
  
}
