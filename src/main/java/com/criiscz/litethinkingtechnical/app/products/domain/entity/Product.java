package com.criiscz.litethinkingtechnical.app.products.domain.entity;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import lombok.Builder;

import java.util.List;

@Builder
public record Product(
        String code,
        String name,
        String characteristics,
        Double price,
        String currency,
        Company company,
        Long stock,
        List<Category> categories
) {
}
