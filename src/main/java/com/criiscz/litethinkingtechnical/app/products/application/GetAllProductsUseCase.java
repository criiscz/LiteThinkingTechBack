package com.criiscz.litethinkingtechnical.app.products.application;

import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@UseCase
@AllArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public ResponseWithPaginationData<Product> execute(int page, int size, Map<String, Object> filters) {
        return productRepository.findAll(page, size, filters);
    }

    public List<ProductOutput> execute() {
        return productRepository.findAll().stream().map(ProductOutput::fromProduct).toList();
    }
}
