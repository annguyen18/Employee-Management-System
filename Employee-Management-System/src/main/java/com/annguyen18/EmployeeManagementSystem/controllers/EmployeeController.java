package com.annguyen18.EmployeeManagementSystem.controllers;

import com.annguyen18.EmployeeManagementSystem.models.Employee;
import com.annguyen18.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-employees")
public class EmployeeController {
    @Autowired
    EmployeeService empService;
//
//    @GetMapping("")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        return new ResponseEntity<>(empService.getEmployees(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{employeeId}")
//    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") int id) {
//        return new ResponseEntity<>(empService.getEmployeeById(id), HttpStatus.OK);
//    }
//
    @PostMapping("")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp) {
        System.out.println("Controller: " + emp.getFirstName());
        empService.saveEmployee(emp);
        return new ResponseEntity<>(String.format("Employee %s %s added", emp.getFirstName(), emp.getLastName()), HttpStatus.OK);
    }
//
//    @PutMapping("/{employeeId}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") int id,
//                                                   @RequestBody Employee employeeDetails) {
//        employeeDetails.setId(id);
//        Employee updatedEmp = empService.updateEmployee(employeeDetails);
//        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
//    }
}
