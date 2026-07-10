package com.atmaram.ems.service;

import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest request);

    Page<EmployeeResponse> getAllEmployees(int page, int size, String sortBy);

    Page<EmployeeResponse> searchEmployees(
        String firstName,
        String lastName,
        String department,
        String designation,
        String status,
        int page,
        int size,
        String sortBy
        );

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, CreateEmployeeRequest request);

    void deleteEmployee(Long id);

    // Search Methods
    List<EmployeeResponse> searchByFirstName(String firstName);

    List<EmployeeResponse> searchByLastName(String lastName);

    EmployeeResponse searchByEmail(String email);

    List<EmployeeResponse> searchByDepartment(String department);

    List<EmployeeResponse> searchByDesignation(String designation);

    
}