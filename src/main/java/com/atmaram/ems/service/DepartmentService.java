package com.atmaram.ems.service;

import com.atmaram.ems.dto.request.CreateDepartmentRequest;
import com.atmaram.ems.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(CreateDepartmentRequest request);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse updateDepartment(
            Long id,
            CreateDepartmentRequest request);

    void deleteDepartment(Long id);
}