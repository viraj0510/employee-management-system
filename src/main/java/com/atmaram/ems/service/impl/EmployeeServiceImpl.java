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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.atmaram.ems.specification.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;

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
                .department(request.getDepartment())
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
public Page<EmployeeResponse> searchEmployees(
        String firstName,
        String lastName,
        String department,
        String designation,
        String status,
        int page,
        int size,
        String sortBy) {

    Specification<Employee> specification =
            Specification.where(EmployeeSpecification.hasFirstName(firstName))
                    .and(EmployeeSpecification.hasLastName(lastName))
                    .and(EmployeeSpecification.hasDepartment(department))
                    .and(EmployeeSpecification.hasDesignation(designation))
                    .and(EmployeeSpecification.hasStatus(status));

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(sortBy).ascending());

    return employeeRepository.findAll(specification, pageable)
            .map(this::mapToResponse);
}

    @Override
public Page<EmployeeResponse> getAllEmployees(int page, int size, String sortBy) {

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(sortBy).ascending()
    );

    return employeeRepository.findAll(pageable)
            .map(this::mapToResponse);
}


@Override
public EmployeeResponse getEmployeeById(Long id) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
        new ResourceNotFoundException("Employee not found"));

    return mapToResponse(employee);
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
    employee.setDepartment(request.getDepartment());
    employee.setDesignation(request.getDesignation());
    employee.setSalary(request.getSalary());
    employee.setStatus(request.getStatus());

    Employee updatedEmployee = employeeRepository.save(employee);

    return mapToResponse(updatedEmployee);
}
@Override
public void deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found"));

    employeeRepository.delete(employee);
}

@Override
public List<EmployeeResponse> searchByFirstName(String firstName) {
    return employeeRepository.findByFirstNameContainingIgnoreCase(firstName)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
@Override
public List<EmployeeResponse> searchByLastName(String lastName) {
    return employeeRepository.findByLastNameContainingIgnoreCase(lastName)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
@Override
public EmployeeResponse searchByEmail(String email) {
    Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    return mapToResponse(employee);
}
@Override
public List<EmployeeResponse> searchByDepartment(String department) {
    return employeeRepository.findByDepartmentContainingIgnoreCase(department)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
@Override
public List<EmployeeResponse> searchByDesignation(String designation) {
    return employeeRepository.findByDesignationContainingIgnoreCase(designation)
            .stream()
            .map(this::mapToResponse)
            .toList();
}

private EmployeeResponse mapToResponse(Employee employee) {

    return EmployeeResponse.builder()
            .id(employee.getId())
            .employeeCode(employee.getEmployeeCode())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .email(employee.getEmail())
            .department(employee.getDepartment())
            .designation(employee.getDesignation())
            .status(employee.getStatus())
            .build();
}
}