package com.microservices.employeeservice.service.impl;

import com.microservices.employeeservice.dto.DepartmentDto;
import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.dto.EmployeeResponseDto;
import com.microservices.employeeservice.dto.OrganizationDto;
import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.mapper.EmployeeMapper;
import com.microservices.employeeservice.repository.EmployeeRepository;
import com.microservices.employeeservice.service.APIClient;
import com.microservices.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private APIClient apiClient;

    private WebClient.Builder webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getFirstName(), employeeDto.getEmail(), employeeDto.getDepartmentCode(), employeeDto.getOrganizationCode());
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public EmployeeResponseDto getEmployeeById(Long employeeId) {

        logger.info("inside getEmployeeById() method");
        Optional<Employee> employee = employeeRepository.findById(employeeId);

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.get().getDepartmentCode());

        DepartmentDto departmentDto = webClient.build().get()
                .uri("http://DEPARTMENT-SERVICE/api/departments?departmentCode=" + employee.get().getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.build().get()
                .uri("http://ORGANIZATION-SERVICE/api/organizations/" + employee.get().getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto =  EmployeeMapper.mapToEmployeeDto(employee.get());

        return new EmployeeResponseDto(employeeDto, departmentDto, organizationDto);
    }

    public EmployeeResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        logger.info("inside getDefaultDepartment() method");
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentName("R&D");
        departmentDto.setDepartmentDescription("Research and Development");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationCode("ORG123");
        organizationDto.setOrganizationName("Organization");
        organizationDto.setOrganizationDescription("Organization Description");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee.get());

        return new EmployeeResponseDto(employeeDto, departmentDto, organizationDto);
    }
}
