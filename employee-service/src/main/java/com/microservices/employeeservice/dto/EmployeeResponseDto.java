package com.microservices.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganizationDto organizationDto;
}
