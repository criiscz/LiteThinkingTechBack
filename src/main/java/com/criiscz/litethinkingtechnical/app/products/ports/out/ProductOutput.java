package com.criiscz.litethinkingtechnical.app.products.ports.out;

import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;

import java.util.List;

public record ProductOutput(
        String code,
        String name,
        String characteristics,
        Double price,
        String currency,
        Long stock,
        CompanyOutput company,
        List<CategoryOutput> categories

) {

    public static ProductOutput fromProduct(Product product) {
        return new ProductOutput(
                product.code(),
                product.name(),
                product.characteristics(),
                product.price(),
                product.currency(),
                product.stock(),
                com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput.fromCompany(product.company()),
                product.categories().stream().map(CategoryOutput::of).toList()
        );
    }

    public static Product toProduct(ProductOutput productOutput) {
        return new Product(
                productOutput.code(),
                productOutput.name(),
                productOutput.characteristics(),
                productOutput.price(),
                productOutput.currency(),
                com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput.toCompany(productOutput.company()),
                productOutput.stock(),
                productOutput.categories().stream().map(CategoryOutput::to).toList()
        );
    }
}
