package com.example.SalesManagementSystem.controller;

import com.example.SalesManagementSystem.dto.TenantRequest;
import com.example.SalesManagementSystem.dto.TenantResponse;
import com.example.SalesManagementSystem.service.JwtService;
import com.example.SalesManagementSystem.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class HelloControler {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserDetailsService userDetailsService;

  @Autowired
  private TenantService tenantService;


  @GetMapping("/")
  public String greet(){
    return "Welcome to the Sales Management Software";
  }

  @PostMapping("/login/tenant")
  public String login(@RequestBody TenantRequest tenantRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tenantRequest.getEmail(), tenantRequest.getPassword()));

    UserDetails userDetails = userDetailsService.loadUserByUsername(tenantRequest.getEmail());
    return jwtService.generateToken(userDetails);
  }

  @PostMapping("/register/tenant")
  public ResponseEntity<TenantResponse> register(@RequestBody TenantRequest tenantRequest){

    return ResponseEntity.ok(tenantService.createTenant(tenantRequest));
    
  }

  
  
}
