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
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Employee Management", description = "Employee Management REST APIs")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create Employee", description = "Creates a new employee")
@ApiResponses({
    @ApiResponse(responseCode = "201", description = "Employee created successfully"),
    @ApiResponse(responseCode = "409", description = "Employee already exists"),
    @ApiResponse(responseCode = "400", description = "Invalid request")
})

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        EmployeeResponse response = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(summary = "Get All Employees", description = "Returns paginated employee list")

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(

        @RequestParam(defaultValue = "0") int page,

        @RequestParam(defaultValue = "5") int size,

        @RequestParam(defaultValue = "id") String sortBy) {

    return ResponseEntity.ok(
            employeeService.getAllEmployees(page, size, sortBy)
    );
}
@Operation(summary = "Get Employee By ID")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Employee found"),
    @ApiResponse(responseCode = "404", description = "Employee not found")
})

@GetMapping("/{id}")
public ResponseEntity<EmployeeResponse> getEmployeeById(
        @PathVariable Long id) {

    return ResponseEntity.ok(employeeService.getEmployeeById(id));
}

@Operation(summary = "Update Employee")

@PutMapping("/{id}")
public ResponseEntity<EmployeeResponse> updateEmployee(
        @PathVariable Long id,
        @Valid @RequestBody CreateEmployeeRequest request) {

    return ResponseEntity.ok(employeeService.updateEmployee(id, request));
}

@Operation(summary = "Delete Employee")

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

    employeeService.deleteEmployee(id);

    return ResponseEntity.noContent().build();
}

@Operation(summary = "Search Employees", description = "Search employees using optional filters")

@GetMapping("/search")
public ResponseEntity<Page<EmployeeResponse>> searchEmployees(

        @RequestParam(required = false) String firstName,

        @RequestParam(required = false) String lastName,

        @RequestParam(required = false) String department,

        @RequestParam(required = false) String designation,

        @RequestParam(required = false) String status,

        @RequestParam(defaultValue = "0") int page,

        @RequestParam(defaultValue = "10") int size,

        @RequestParam(defaultValue = "id") String sortBy) {

    return ResponseEntity.ok(
            employeeService.searchEmployees(
                    firstName,
                    lastName,
                    department,
                    designation,
                    status,
                    page,
                    size,
                    sortBy
            )
    );
}



}