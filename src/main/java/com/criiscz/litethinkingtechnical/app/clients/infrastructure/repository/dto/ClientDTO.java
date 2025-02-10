package com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Id
    private String id;
    private String name;
    private String phone;
}
