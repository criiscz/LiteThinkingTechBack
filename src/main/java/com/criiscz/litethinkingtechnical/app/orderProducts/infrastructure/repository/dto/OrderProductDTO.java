package com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.dto;

import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.dto.OrderDTO;
import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_products")
public class OrderProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}
