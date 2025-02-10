package com.criiscz.litethinkingtechnical.app.orders.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.app.orders.ports.in.OrderInput;
import com.criiscz.litethinkingtechnical.app.orders.ports.out.OrderOutput;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@UseCase
@AllArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public OrderOutput execute(OrderInput order) {
        Client client = clientRepository.getClient(order.clientId())
                .orElseThrow(() -> new ItemNotFoundException("Client not found"));
        List<Product> products = order.productIdsList().stream().map(
                productId -> productRepository.findById(productId
                ).orElseThrow(() -> new ItemNotFoundException("Product not found"))).toList();
        return OrderOutput.fromOrder(orderRepository.saveOrder(OrderInput.toOrder(client, products)));
    }
}
