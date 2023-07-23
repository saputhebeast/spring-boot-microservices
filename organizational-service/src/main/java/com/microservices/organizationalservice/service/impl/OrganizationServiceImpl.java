package com.microservices.organizationalservice.service.impl;

import com.microservices.organizationalservice.dto.OrganizationDto;
import com.microservices.organizationalservice.entity.Organization;
import com.microservices.organizationalservice.mapper.OrganizationMapper;
import com.microservices.organizationalservice.repository.OrganizationRepository;
import com.microservices.organizationalservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization saveOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(saveOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
