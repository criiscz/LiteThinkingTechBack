package com.criiscz.litethinkingtechnical.app.products.application;

import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public boolean execute(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Product not found"));
        return productRepository.deleteById(product.code());
    }
}
