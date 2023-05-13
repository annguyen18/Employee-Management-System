package com.annguyen18.EmployeeManagementSystem.repositories;

import com.annguyen18.EmployeeManagementSystem.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee save(Employee employee);
//    Page<Employee> findAll(Sort sort, int pageNo, int pageSize);
    List<Employee> findAll(Sort sort);
    List<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);
    Optional<Employee> findById(int id);
    Employee findByLastName(String lastName);
    void deleteById(int id);
    List<Employee> findByDepartmentId(int id);
    @Query(value = "SELECT * FROM employee WHERE first_name LIKE ?1% OR last_name LIKE ?1%", nativeQuery = true)
    List<Employee> findByFirstNameLastName(String keyword);

//    List<Employee> findByFirstNameStartsWithOrLastNameStartsWith(String firstName, String lastName);
//    List<Employee> findByLastNameContaining(String keyword);
    int countByDepartmentId(int id);
}
