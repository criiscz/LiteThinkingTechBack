package com.criiscz.litethinkingtechnical.app.clients.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteClientUseCase {

    private final ClientRepository clientRepository;

    public boolean execute(String clientId) {
        Client client = clientRepository.getClient(clientId).orElseThrow(() -> new ItemNotFoundException("Client not found"));
        return clientRepository.deleteClient(client.id());
    }

}
