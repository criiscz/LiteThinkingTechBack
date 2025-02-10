package com.criiscz.litethinkingtechnical.app.clients.application;

import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.in.ClientInput;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public ClientOutput execute(ClientInput clientInput, String clientId) {
        return ClientOutput.fromClient(clientRepository.updateClient(ClientInput.to(clientInput), clientId));
    }
}
