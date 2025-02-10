package com.criiscz.litethinkingtechnical.app.companies.application;

import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@UseCase
public class GetAllCompaniesUseCase {
    private final CompanyRepository companyRepository;

    public ResponseWithPaginationData<Company> execute(int page, int size, Map<String, Object> filters) {
        return companyRepository.getAllCompanies(page, size, filters);
    }
}
