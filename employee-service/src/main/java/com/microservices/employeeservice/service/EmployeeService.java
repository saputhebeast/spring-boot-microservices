package com.microservices.employeeservice.service;

import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.dto.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeResponseDto getEmployeeById(Long employeeId);
}
