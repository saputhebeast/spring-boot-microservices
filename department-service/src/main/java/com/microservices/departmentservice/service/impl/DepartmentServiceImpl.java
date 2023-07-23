package com.microservices.departmentservice.service.impl;

import com.microservices.departmentservice.dto.DepartmentDto;
import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.mapper.DepartmentMapper;
import com.microservices.departmentservice.repository.DepartmentRepository;
import com.microservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // convert department dto to department jpa entity
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department saveDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByDepartmentCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return DepartmentMapper.mapToDepartmentDto(department);
    }
}
