package com.criiscz.litethinkingtechnical.app.companies.ports.in;


import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;

public record CompanyInput(
        String NIT,
        String name,
        String phone,
        String address
) {

    public static CompanyInput of(String NIT, String name, String phone, String address) {
        return new CompanyInput(NIT, name, phone, address);
    }

    public static Company to(CompanyInput clientInput) {
        return Company.builder()
                .NIT(clientInput.NIT())
                .name(clientInput.name())
                .phone(clientInput.phone())
                .address(clientInput.address())
                .build();
    }
}
