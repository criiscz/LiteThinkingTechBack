package com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import com.criiscz.litethinkingtechnical.app.orderProducts.domain.repository.OrderProductRepository;
import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.mapper.OrderProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderProductRepositoryJPA productRepositoryjpa;


    @Override
    public OrderProduct save(OrderProduct product) {
        return OrderProductMapper.toDomain(productRepositoryjpa.save(OrderProductMapper.toDTO(product)));
    }

    @Override
    public Optional<OrderProduct> findById(Long id) {
        return productRepositoryjpa.findById(id).map(OrderProductMapper::toDomain);
    }

    @Override
    public boolean deleteById(Long id) {
        var orderProduct = productRepositoryjpa.findById(id);
        if (orderProduct.isPresent()) {
            productRepositoryjpa.delete(orderProduct.get());
            return true;
        }
        return false;
    }

    @Override
    public List<OrderProduct> findAll() {
        return productRepositoryjpa.findAll().stream().map(OrderProductMapper::toDomain).toList();
    }

    @Override
    public OrderProduct update(Long id, OrderProduct product) {
        return OrderProductMapper.toDomain(productRepositoryjpa.save(OrderProductMapper.toDTO(product)));
    }
}
