package com.criiscz.litethinkingtechnical.app.orders.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.app.orders.ports.in.OrderInput;
import com.criiscz.litethinkingtechnical.app.orders.ports.out.OrderOutput;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
@UseCase
@AllArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public OrderOutput execute(OrderInput order) {
        Client client = clientRepository.getClient(order.clientId())
                .orElseThrow(() -> new ItemNotFoundException("Client not found"));

        var orderId = generateOrderId();

        List<OrderProduct> products = order.productIdsList()
                .stream()
                .map(productId ->
                        productRepository
                                .findById(productId.productCode())
                                .map(product -> OrderProduct.builder()
                                        .productCode(productId.productCode())
                                        .quantity(productId.quantity())
                                        .price(productId.price())
                                        .orderId(orderId)
                                        .build())
                                .orElseThrow(() -> new ItemNotFoundException("Product not found"))
                )
                .toList();

        // remove the products from the stock
        products.forEach(product -> {
            var productToUpdate = productRepository.findById(product.productCode())
                    .orElseThrow(() -> new ItemNotFoundException("Product not found"));
            Product.builder().
                    code(productToUpdate.code())
                    .name(productToUpdate.name())
                    .currency(productToUpdate.currency())
                    .characteristics(productToUpdate.characteristics())
                    .stock(productToUpdate.stock() - product.quantity())
                    .price(productToUpdate.price())
                    .categories(productToUpdate.categories())
                    .company(productToUpdate.company())
                    .build();
            productRepository.save(productToUpdate);
        });

        var resultOrder = orderRepository.saveOrder(OrderInput.toOrder(orderId, client, products));

        return OrderOutput.fromOrder(resultOrder);
    }

    private Long generateOrderId() {
        return new Date().getTime();
    }
}
