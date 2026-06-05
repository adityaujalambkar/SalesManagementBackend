package com.example.SalesManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;


@Entity
@Table(name="users")
@Getter
@Setter
public class TenantUser{

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String password;

    private boolean active;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private Role role;
}