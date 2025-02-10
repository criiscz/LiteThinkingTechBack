package com.criiscz.litethinkingtechnical.app.companies.domain.repository;

import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;

import java.util.Map;
import java.util.Optional;

public interface CompanyRepository {

    Company saveCompany(Company company);
    boolean deleteCompany(String id);
    Optional<Company> getCompanyById(String id);
    ResponseWithPaginationData<Company> getAllCompanies(int page, int size, Map<String, Object> filters);
    Company updateCompany(Company company, String id);
}
