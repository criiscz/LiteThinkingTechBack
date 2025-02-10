package com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.mapper.OrderMapper;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJPA orderRepositoryJpa;

    @Override
    public Order saveOrder(Order order) {
        return OrderMapper.toDomain(orderRepositoryJpa.save(OrderMapper.toDTO(order)));
    }

    @Override
    public boolean deleteOrder(Long id) {
        var order = orderRepositoryJpa.findById(id);
        if (order.isPresent()) {
            orderRepositoryJpa.delete(order.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepositoryJpa.findById(id).map(OrderMapper::toDomain);
    }

    @Override
    public ResponseWithPaginationData<Order> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var orders = orderRepositoryJpa.findAll(pageable);
        return new ResponseWithPaginationData<>(
                orders.getContent().stream().map(OrderMapper::toDomain).toList(),
                Pagination.builder()
                        .page(page)
                        .size(size)
                        .totalItems(orders.getTotalElements())
                        .totalPages(orders.getTotalPages())
                        .hasNext(orders.hasNext())
                        .build()
        );
    }
}
