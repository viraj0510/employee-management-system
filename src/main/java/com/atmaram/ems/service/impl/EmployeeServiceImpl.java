package com.atmaram.ems.service.impl;

import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;
import com.atmaram.ems.entity.Employee;
import com.atmaram.ems.repository.EmployeeRepository;
import com.atmaram.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
            throw new RuntimeException("Employee code already exists");
        }

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Employee email already exists");
        }

        Employee employee = Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .joiningDate(request.getJoiningDate())
                .designation(request.getDesignation())
                .salary(request.getSalary())
                .status(request.getStatus())
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .id(savedEmployee.getId())
                .employeeCode(savedEmployee.getEmployeeCode())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail())
                .designation(savedEmployee.getDesignation())
                .status(savedEmployee.getStatus())
                .build();
    }
}