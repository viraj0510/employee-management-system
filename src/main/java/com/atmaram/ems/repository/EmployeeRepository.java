package com.atmaram.ems.repository;

import com.atmaram.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
                JpaSpecificationExecutor<Employee> {

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    List<Employee> findByDesignationContainingIgnoreCase(String designation);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);
}