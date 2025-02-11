package com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.dto;

import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.dto.ClientDTO;
import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.dto.OrderProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientDTO client;

    @Column(name = "product_list")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderProductDTO> productList;

}
