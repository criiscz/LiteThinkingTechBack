package com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.dto.ClientDTO;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .id(client.id())
                .name(client.name())
                .build();
    }

    public static Client toDomain(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .build();
    }

}
