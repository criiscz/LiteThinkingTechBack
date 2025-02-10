package com.criiscz.litethinkingtechnical.app.products.application;

import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public ProductOutput execute(String id) {
        return productRepository.findById(id)
                .map(ProductOutput::fromProduct)
                .orElseThrow(() -> new ItemNotFoundException("Product not found"));
    }
}
