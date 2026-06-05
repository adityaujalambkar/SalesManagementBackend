package com.example.SalesManagementSystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;


@Entity
public class Role{

    @Id
    private UUID id;

    private String roleName;

    @ManyToOne
    private Tenant tenant;
}
