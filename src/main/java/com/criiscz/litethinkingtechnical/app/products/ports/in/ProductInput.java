package com.criiscz.litethinkingtechnical.app.products.ports.in;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public record ProductInput(
        String code,
        String name,
        String characteristics,
        Double price,
        String currency,
        Long stock,
        String companyId,
        List<Long> categories
) {

    public static ProductInput of(Product product) {
        return new ProductInput(
                product.code(),
                product.name(),
                product.characteristics(),
                product.price(),
                product.currency(),
                product.stock(),
                product.company().NIT(),
                product.categories().stream().map(Category::id).collect(Collectors.toList())
        );
    }

    public static Product to(ProductInput productInput, Company company, List<Category> categories) {
        return Product.builder()
                .code(productInput.code())
                .name(productInput.name())
                .characteristics(productInput.characteristics())
                .price(productInput.price())
                .currency(productInput.currency())
                .stock(productInput.stock())
                .company(company)
                .categories(categories)
                .build();
    }
}
