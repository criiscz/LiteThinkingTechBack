package com.criiscz.litethinkingtechnical.app.products.domain.repository;

import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    boolean deleteById(String id);
    ResponseWithPaginationData<Product> findAll(int page, int size, Map<String, Object> filters);

    List<Product> findAll();

    Product update(String id, Product product);
}
