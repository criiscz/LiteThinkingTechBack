package com.criiscz.litethinkingtechnical.app.orderProducts.domain.repository;

import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct product);
    Optional<OrderProduct> findById(Long id);
    boolean deleteById(Long id);
    List<OrderProduct> findAll();
    OrderProduct update(Long id, OrderProduct product);
}
