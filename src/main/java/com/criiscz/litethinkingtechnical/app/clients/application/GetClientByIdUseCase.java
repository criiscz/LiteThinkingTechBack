package com.criiscz.litethinkingtechnical.app.clients.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GetClientByIdUseCase {
    private final ClientRepository clientRepository;


    public ClientOutput execute(String id) {
        return clientRepository.getClient(id)
                .map(ClientOutput::fromClient)
                .orElseThrow(() -> new ItemNotFoundException("Client not found"));
    }
}
