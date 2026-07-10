package com.atmaram.ems.repository;

import com.atmaram.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    List<Employee> findByDesignationContainingIgnoreCase(String designation);
}