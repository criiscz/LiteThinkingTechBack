package com.criiscz.litethinkingtechnical.app.companies.application;

import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.in.CompanyInput;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CompanyOutput execute(CompanyInput clientInput, String id) {
        return CompanyOutput.fromCompany(companyRepository.updateCompany(CompanyInput.to(clientInput), id));
    }
}
