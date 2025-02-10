package com.criiscz.litethinkingtechnical.app.products.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.app.products.ports.in.ProductInput;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@UseCase
@AllArgsConstructor
public class UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    public ProductOutput execute(ProductInput productInput, String productId) {
        Company company = companyRepository.getCompanyById(productInput.companyId()).orElseThrow(() -> new ItemNotFoundException("Company not found"));
        List<Category> categories = productInput.categories()
                .stream()
                .map(categoryId ->
                        categoryRepository
                                .findById(categoryId)
                                .orElseThrow(() -> new ItemNotFoundException("Category not found"))
                )
                .toList();

        return ProductOutput.fromProduct(productRepository.update(productId, ProductInput.to(productInput, company, categories)));
    }

}
