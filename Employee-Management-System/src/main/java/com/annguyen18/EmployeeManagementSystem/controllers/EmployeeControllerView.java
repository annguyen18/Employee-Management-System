package com.annguyen18.EmployeeManagementSystem.controllers;

import com.annguyen18.EmployeeManagementSystem.models.Department;
import com.annguyen18.EmployeeManagementSystem.models.Employee;
import com.annguyen18.EmployeeManagementSystem.services.DepartmentService;
import com.annguyen18.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/employees")
public class EmployeeControllerView {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

//    @GetMapping("")
//    public String getEmployees(@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
//                               @RequestParam(value = "pageNo", required = false, defaultValue = "") String pageNo,
//                               @RequestParam(value = "pageSize", required = false, defaultValue = "") String pageSize,
//                               Model model) {
//        if (sort.equals("last-name-asc"))
//            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.ASC, "lastName")));
//        if (sort.equals("last-name-desc"))
//            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.DESC, "lastName")));
//        if (sort.equals("department"))
//            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.ASC, "department")));
//        if (sort.equals(""))
//            model.addAttribute("employees", employeeService.getEmployees());
////        else
////            model.addAttribute("employees", employeeService.getEmployees(null));
//        return "employees";
//    }

    @GetMapping("")
    public String getEmployees(@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
                               @RequestParam("search") Optional<String> keyword,
                               Model model) {
        if (sort.equals("last-name-asc"))
            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.ASC, "lastName")));
        if (sort.equals("last-name-desc"))
            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.DESC, "lastName")));
        if (sort.equals("department"))
            model.addAttribute("employees", employeeService.getEmployees(Sort.by(Sort.Direction.ASC, "department")));
        if (sort.equals(""))
            model.addAttribute("employees", employeeService.getEmployees());
//        else
//            model.addAttribute("employees", employeeService.getEmployees(null));
        if (keyword.isPresent()) {
            List<Employee> searchResults = employeeService.findEmployeeByFirstNameOrLastName(keyword.get());
            model.addAttribute("employees", searchResults);
        }
        return "employees";
    }

    @GetMapping("/2")
    public String getEmployeesPaging(@RequestParam(value="page", defaultValue = "0", required = false) Integer pageNo,
                                     @RequestParam(value="pageSize", defaultValue = "10", required = false) Integer pageSize,
                                     @RequestParam(value="sort-by", defaultValue = "gender", required = false) String sortBy,
                                     @RequestParam(value="sort-direction", defaultValue = "gender", required = false) String sortDirection,
                               Model model) {
        /*
        1 : fname a-z
        2 : fname z-a
        3 : lname a-z
        4 : lname z-a
        5 : dept
         */
        Sort.Direction direction = null;
        String property = "";
        if (sortBy.equals("1") || sortBy == null){
            direction = Sort.Direction.ASC;
            property = "firstName";
        }

        if (sortBy.equals("2")){
            direction = Sort.Direction.DESC;
            property = "firstName";
        }

        if (sortBy.equals("3")){
            direction = Sort.Direction.ASC;
            property = "lastName";
        }

        if (sortBy.equals("4")){
            direction = Sort.Direction.DESC;
            property = "lastName";
        }

        if (sortBy.equals("5")){
            direction = Sort.Direction.ASC;
            property = "department";
        }

        if (sortBy.equals("5")){
            direction = Sort.Direction.DESC;
            property = "department";
        }

        assert direction != null;
        Sort sort = Sort.by(direction, property);
        List<Employee> list = employeeService.getEmployees(pageNo, pageSize, sort);
        model.addAttribute("employees", list);

        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable("id") String id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(Integer.parseInt(id)));
        return "employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployeeById(Integer.parseInt(id));
        return "redirect:/employees";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        List<Department> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
        return "add";
    }

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute("employee") Employee e) {
        employeeService.saveEmployee(e);
        return "redirect:/employees";
    }

}
