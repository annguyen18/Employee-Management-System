package com.annguyen18.EmployeeManagementSystem.controllers;

import com.annguyen18.EmployeeManagementSystem.models.Employee;
import com.annguyen18.EmployeeManagementSystem.services.DepartmentService;
import com.annguyen18.EmployeeManagementSystem.models.Department;
import com.annguyen18.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public String getDepartments(Model model) {
        model.addAttribute("depts", departmentService.getDepartments());
        model.addAttribute("employeeService", employeeService);
        return "departments";
    }

    @GetMapping("/{name}")
    public String getDepartment(@PathVariable("name") String name, Model model) {
        Department dept = departmentService.getDepartment(name);

        model.addAttribute("department", dept);
        return "department";
    }
}
