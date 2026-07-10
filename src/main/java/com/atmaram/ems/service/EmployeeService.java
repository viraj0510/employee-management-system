package com.atmaram.ems.service;
import org.springframework.data.domain.Page;
import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest request);
    
    Page<EmployeeResponse> getAllEmployees(int page, int size, String sortBy);
    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, CreateEmployeeRequest request);
    List<EmployeeResponse> searchEmployees(String keyword);
    void deleteEmployee(Long id);
}