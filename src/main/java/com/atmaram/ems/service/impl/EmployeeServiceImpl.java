package com.atmaram.ems.service.impl;

import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;
import com.atmaram.ems.entity.Employee;
import com.atmaram.ems.exception.DuplicateResourceException;
import com.atmaram.ems.exception.ResourceNotFoundException;
import com.atmaram.ems.repository.EmployeeRepository;
import com.atmaram.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
    throw new DuplicateResourceException("Employee code already exists");
}

        if (employeeRepository.existsByEmail(request.getEmail())) {
    throw new DuplicateResourceException("Employee email already exists");
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

    @Override
    public List<EmployeeResponse> getAllEmployees() {

    return employeeRepository.findAll()
            .stream()
            .map(employee -> EmployeeResponse.builder()
                    .id(employee.getId())
                    .employeeCode(employee.getEmployeeCode())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .designation(employee.getDesignation())
                    .status(employee.getStatus())
                    .build())
            .collect(Collectors.toList());
}
@Override
public EmployeeResponse getEmployeeById(Long id) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
        new ResourceNotFoundException("Employee not found"));

    return EmployeeResponse.builder()
            .id(employee.getId())
            .employeeCode(employee.getEmployeeCode())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .email(employee.getEmail())
            .designation(employee.getDesignation())
            .status(employee.getStatus())
            .build();
}
@Override
public EmployeeResponse updateEmployee(Long id, CreateEmployeeRequest request) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found"));

    employee.setEmployeeCode(request.getEmployeeCode());
    employee.setFirstName(request.getFirstName());
    employee.setLastName(request.getLastName());
    employee.setEmail(request.getEmail());
    employee.setPhone(request.getPhone());
    employee.setGender(request.getGender());
    employee.setDateOfBirth(request.getDateOfBirth());
    employee.setJoiningDate(request.getJoiningDate());
    employee.setDesignation(request.getDesignation());
    employee.setSalary(request.getSalary());
    employee.setStatus(request.getStatus());

    Employee updatedEmployee = employeeRepository.save(employee);

    return EmployeeResponse.builder()
            .id(updatedEmployee.getId())
            .employeeCode(updatedEmployee.getEmployeeCode())
            .firstName(updatedEmployee.getFirstName())
            .lastName(updatedEmployee.getLastName())
            .email(updatedEmployee.getEmail())
            .designation(updatedEmployee.getDesignation())
            .status(updatedEmployee.getStatus())
            .build();
}
@Override
public void deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found"));

    employeeRepository.delete(employee);
}
}