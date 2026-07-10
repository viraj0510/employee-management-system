package com.atmaram.ems.service.impl;

import com.atmaram.ems.dto.request.CreateDepartmentRequest;
import com.atmaram.ems.dto.response.DepartmentResponse;
import com.atmaram.ems.entity.Department;
import com.atmaram.ems.exception.DuplicateResourceException;
import com.atmaram.ems.exception.ResourceNotFoundException;
import com.atmaram.ems.repository.DepartmentRepository;
import com.atmaram.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {

        if (departmentRepository.existsByDepartmentCode(request.getDepartmentCode())) {
            throw new DuplicateResourceException("Department code already exists");
        }

        if (departmentRepository.existsByDepartmentName(request.getDepartmentName())) {
            throw new DuplicateResourceException("Department name already exists");
        }

        Department department = Department.builder()
                .departmentCode(request.getDepartmentCode())
                .departmentName(request.getDepartmentName())
                .location(request.getLocation())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();

        return mapToResponse(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        return mapToResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id,
                                               CreateDepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        department.setDepartmentCode(request.getDepartmentCode());
        department.setDepartmentName(request.getDepartmentName());
        department.setLocation(request.getLocation());
        department.setDescription(request.getDescription());
        department.setStatus(request.getStatus());

        return mapToResponse(
                departmentRepository.save(department)
        );
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        departmentRepository.delete(department);
    }

    private DepartmentResponse mapToResponse(Department department) {

        return DepartmentResponse.builder()
                .id(department.getId())
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .location(department.getLocation())
                .description(department.getDescription())
                .status(department.getStatus())
                .build();
    }
}