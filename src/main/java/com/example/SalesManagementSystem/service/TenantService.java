package com.example.SalesManagementSystem.service;

import com.example.SalesManagementSystem.dto.TenantRequest;
import com.example.SalesManagementSystem.dto.TenantResponse;
import com.example.SalesManagementSystem.model.Tenant;
import com.example.SalesManagementSystem.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class TenantService {

  @Autowired
  private TenantRepo tenantRepo;

  private final PasswordEncoder passwordEncoder;

  public TenantService(PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
  }

  public TenantResponse createTenant(TenantRequest tenantRequest){


    Tenant tenant = new Tenant();
    tenant.setEmail(tenantRequest.getEmail());
    tenant.setPassword(passwordEncoder.encode(tenantRequest.getPassword()));

    tenantRepo.save(tenant);

    TenantResponse tenantResponse = new TenantResponse();
    tenantResponse.setEmail(tenantRequest.getEmail());

    return tenantResponse;

  }
  
}
