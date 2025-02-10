package com.criiscz.litethinkingtechnical.app.companies.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteCompanyUseCase {

    private final CompanyRepository companyRepository;

    public boolean execute(String nit) {
        Company company = companyRepository.getCompanyById(nit).orElseThrow(() -> new ItemNotFoundException("Company not found"));
        return companyRepository.deleteCompany(company.NIT());
    }

}
