package com.example.SalesManagementSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

  @GetMapping("/")
  public String greet(){
    return "Welcome to the Sales Management Software";
  }
  
}
