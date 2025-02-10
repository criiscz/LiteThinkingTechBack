package com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.dto.CompanyDTO;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company) {
        return CompanyDTO.builder()
                .NIT(company.NIT())
                .name(company.name())
                .address(company.address())
                .phone(company.phone())
                .build();
    }

    public static Company toDomain(CompanyDTO companyDTO) {
        return Company.builder()
                .NIT(companyDTO.getNIT())
                .name(companyDTO.getName())
                .address(companyDTO.getAddress())
                .phone(companyDTO.getPhone())
                .build();
    }

}
