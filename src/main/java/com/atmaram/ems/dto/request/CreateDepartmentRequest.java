package com.atmaram.ems.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateDepartmentRequest {

    @NotBlank
    private String departmentCode;

    @NotBlank
    private String departmentName;

    private String location;

    private String description;

    private String status;
}