package com.criiscz.litethinkingtechnical.app.companies.application;

import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GetCompanyByIdUseCase {
    private final CompanyRepository companyRepository;


    public CompanyOutput execute(String id) {
        return companyRepository.getCompanyById(id)
                .map(CompanyOutput::fromCompany)
                .orElseThrow(() -> new ItemNotFoundException("Client not found"));
    }
}
