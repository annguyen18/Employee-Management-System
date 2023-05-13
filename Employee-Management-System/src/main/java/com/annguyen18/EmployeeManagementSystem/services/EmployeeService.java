package com.annguyen18.EmployeeManagementSystem.services;

import com.annguyen18.EmployeeManagementSystem.repositories.EmployeeRepository;
import com.annguyen18.EmployeeManagementSystem.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

//    public List<Employee> getEmployees(Sort sort, int pageNo, int pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//
//        Page<Employee> resultPage = employeeRepository.findAll(sort, pageNo, pageSize);
//
//        if (resultPage.hasContent())
//            return resultPage.getContent();
//        return new ArrayList<Employee>();
//    }

    public List<Employee> getEmployees(Integer pageNo, Integer pageSize, Sort sort) {
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }

    public List<Employee> getEmployees(Sort sort) {
        return employeeRepository.findAll(sort);
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee emp) {
        Optional<Employee> existingEmp = employeeRepository.findById(emp.getId());
        existingEmp.ifPresent(e -> {
            if (emp.getLastName() != null) {
                e.setLastName(emp.getLastName());
            }
            if (emp.getPhoneNumber() != null) {
                e.setPhoneNumber(emp.getPhoneNumber());
            }
            if (emp.getEmail() != null) {
                e.setEmail(emp.getEmail());
            }
            employeeRepository.save(e);
        });
        return existingEmp.orElse(null);
    }

    public List<Employee> getAllEmployeesByDepartmentId(int id) {
        return employeeRepository.findByDepartmentId(id);
    }

    public List<Employee> findEmployeeByFirstNameOrLastName (String keyword) {
        return employeeRepository.findByFirstNameLastName(keyword);
    }

    public int count (int id) {
        return employeeRepository.countByDepartmentId(id);
    }
}
