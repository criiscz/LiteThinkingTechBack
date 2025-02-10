package com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @Id
    private String NIT;
    private String name;
    private String address;
    private String phone;
}
