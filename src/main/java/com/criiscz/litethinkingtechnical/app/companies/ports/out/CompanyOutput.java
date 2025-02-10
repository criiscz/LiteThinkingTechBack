package com.criiscz.litethinkingtechnical.app.companies.ports.out;


import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;

public record CompanyOutput(
        String NIT,
        String name,
        String phone,
        String address
) {

    public static CompanyOutput fromCompany(Company client) {
        return new CompanyOutput(
                client.NIT(),
                client.name(),
                client.phone(),
                client.address()
        );
    }

    public static Company toCompany(CompanyOutput companyOutput) {
        return new Company(
                companyOutput.NIT(),
                companyOutput.name(),
                companyOutput.phone(),
                companyOutput.address()
        );
    }
}
