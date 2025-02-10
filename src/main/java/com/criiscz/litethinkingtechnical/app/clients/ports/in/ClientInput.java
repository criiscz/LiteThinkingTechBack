package com.criiscz.litethinkingtechnical.app.clients.ports.in;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;

public record ClientInput(
        String id,
        String name,
        String phone
) {

    public static ClientInput of(String id, String name, String phone) {
        return new ClientInput(id, name, phone);
    }

    public static Client to(ClientInput clientInput) {
        return Client.builder()
                .id(clientInput.id())
                .name(clientInput.name())
                .phone(clientInput.phone())
                .build();
    }
}
