package com.atmaram.ems.controller;

import com.atmaram.ems.dto.request.CreateEmployeeRequest;
import com.atmaram.ems.dto.response.EmployeeResponse;
import com.atmaram.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        EmployeeResponse response = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

    return ResponseEntity.ok(employeeService.getAllEmployees());
}
@GetMapping("/{id}")
public ResponseEntity<EmployeeResponse> getEmployeeById(
        @PathVariable Long id) {

    return ResponseEntity.ok(employeeService.getEmployeeById(id));
}
@PutMapping("/{id}")
public ResponseEntity<EmployeeResponse> updateEmployee(
        @PathVariable Long id,
        @Valid @RequestBody CreateEmployeeRequest request) {

    return ResponseEntity.ok(employeeService.updateEmployee(id, request));
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

    employeeService.deleteEmployee(id);

    return ResponseEntity.noContent().build();
}
}