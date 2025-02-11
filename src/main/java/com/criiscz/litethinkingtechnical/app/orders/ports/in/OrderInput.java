package com.criiscz.litethinkingtechnical.app.orders.ports.in;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.orderProducts.ports.in.OrderProductInput;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;

import java.util.List;

public record OrderInput(
        String clientId,
        List<OrderProductInput> productIdsList
) {

    public static Order toOrder(Client client, List<OrderProduct> products) {
        return new Order(
                null,
                client,
                products
        );
    }
    public static Order toOrder(Long id, Client client, List<OrderProduct> products) {
        return new Order(
                id,
                client,
                products
        );
    }


}
