package com.example.SalesManagementSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NexaAuthenticationController {

  @GetMapping("/HelloNexa")
  public String nexaAuthentication(){
    return "Hey Hi, You are authenticated user";
  }
  
}
