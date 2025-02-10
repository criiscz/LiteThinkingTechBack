package com.criiscz.litethinkingtechnical.app.orders.ports.in;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public record OrderInput(
        String clientId,
        List<String> productIdsList
) {

    public static OrderInput of(Order order) {
        return new OrderInput(
                order.client().id(),
                order.productList().stream().map(Product::code).toList()
        );
    }

    public static Order toOrder(Client client, List<Product> products) {
        return new Order(
                null,
                client,
                products
        );
    }
}
