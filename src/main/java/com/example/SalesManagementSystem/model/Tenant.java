package com.example.SalesManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name="Customer")
public class Tenant {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String password;

}