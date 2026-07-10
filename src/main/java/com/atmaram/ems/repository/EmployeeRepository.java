package com.atmaram.ems.repository;

import com.atmaram.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    Optional<Employee> findByEmployeeCode(String employeeCode);
    
    @Query("""
SELECT e FROM Employee e
WHERE
LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
List<Employee> searchEmployees(@Param("keyword") String keyword);

}

