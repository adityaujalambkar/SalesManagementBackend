package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.model.TenantUser;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TenantUserRepo extends JpaRepository<TenantUser,UUID> {

  Optional<TenantUser> findByEmail(String email);
  
}
