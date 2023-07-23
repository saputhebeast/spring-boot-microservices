package com.microservices.employeeservice.service;

import com.microservices.employeeservice.dto.DepartmentDto;
import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping(value = "api/departments")
    DepartmentDto getDepartment(@NonNull String departmentCode);
}
