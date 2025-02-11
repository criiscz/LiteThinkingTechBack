package com.criiscz.litethinkingtechnical.app.clients.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@UseCase
public class GetAllClientsUseCase {
    private final ClientRepository clientRepository;

    public ResponseWithPaginationData<Client> execute(int page, int size, Map<String, Object> filters) {
        return clientRepository.getClients(page, size, filters);
    }

    public List<ClientOutput> execute() {
        return clientRepository.getClients().stream().map(ClientOutput::fromClient).toList();
    }
}
