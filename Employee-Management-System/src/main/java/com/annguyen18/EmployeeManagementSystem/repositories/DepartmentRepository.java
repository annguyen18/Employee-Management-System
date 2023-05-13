package com.annguyen18.EmployeeManagementSystem.repositories;

import com.annguyen18.EmployeeManagementSystem.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAll();
    Department save(Department dept);
    Department findByName(String name);
}
