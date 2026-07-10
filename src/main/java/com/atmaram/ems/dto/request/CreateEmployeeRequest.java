package com.atmaram.ems.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateEmployeeRequest {

    @NotBlank
    private String employeeCode;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String phone;

    private String gender;

    private LocalDate dateOfBirth;

    private LocalDate joiningDate;

    private String designation;

    private BigDecimal salary;

    private String status;
}