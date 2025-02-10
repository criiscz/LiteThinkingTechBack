package com.criiscz.litethinkingtechnical.app.clients.ports.out;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;

public record ClientOutput(
        String id,
        String name,
        String phone
) {

    public static ClientOutput fromClient(Client client) {
        return new ClientOutput(
                client.id(),
                client.name(),
                client.phone()
        );
    }

    public static Client toClient(ClientOutput clientOutput) {
        return new Client(
                clientOutput.id(),
                clientOutput.name(),
                clientOutput.phone()
        );
    }
}
