package com.annguyen18.EmployeeManagementSystem.models;

import com.annguyen18.EmployeeManagementSystem.enums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleType name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Employee> employees;

}
