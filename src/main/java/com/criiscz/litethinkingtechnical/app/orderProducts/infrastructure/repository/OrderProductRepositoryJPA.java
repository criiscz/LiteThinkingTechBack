package com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.dto.OrderProductDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface OrderProductRepositoryJPA extends JpaRepository<OrderProductDTO, Long> {
}
