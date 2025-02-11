package com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import lombok.Builder;

@Builder
public record OrderProduct(
        Long id,
        Long orderId,
        String productCode,
        Integer quantity,
        Double price
) {
}
