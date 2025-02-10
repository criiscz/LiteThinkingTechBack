package com.criiscz.litethinkingtechnical.app.orders.ports.out;

import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;

import java.util.List;

public record OrderOutput(
        Long id,
        ClientOutput client,
        List<ProductOutput> productList
) {

    public static OrderOutput fromOrder(Order order) {
        return new OrderOutput(
                order.id(),
                ClientOutput.fromClient(order.client()),
                order.productList().stream().map(ProductOutput::fromProduct).toList()
        );
    }

    public static Order toOrder(OrderOutput orderOutput) {
        return new Order(
                orderOutput.id(),
                ClientOutput.toClient(orderOutput.client()),
                orderOutput.productList().stream().map(ProductOutput::toProduct).toList()
        );
    }
}
