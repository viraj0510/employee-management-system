package com.atmaram.ems.service;

import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest request);

}