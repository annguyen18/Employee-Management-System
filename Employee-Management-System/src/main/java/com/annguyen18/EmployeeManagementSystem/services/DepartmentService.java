package com.annguyen18.EmployeeManagementSystem.services;

import com.annguyen18.EmployeeManagementSystem.repositories.DepartmentRepository;
import com.annguyen18.EmployeeManagementSystem.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(String name) {
        return departmentRepository.findByName(name);
    }

    public Department saveDepartment(Department dept) {
        return departmentRepository.save(dept);
    }

}
