package com.criiscz.litethinkingtechnical.app.orders.domain.entity;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import lombok.Builder;

import java.util.List;

@Builder
public record Order(
        Long id,
        Client client,
        List<OrderProduct> productList
) {
}
