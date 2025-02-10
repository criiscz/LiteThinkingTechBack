package com.criiscz.litethinkingtechnical.app.clients.domain.repository;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;

import java.util.Map;
import java.util.Optional;

public interface ClientRepository {
    Client saveClient(Client clientInput);
    Client updateClient(Client clientInput, String id);
    boolean deleteClient(String id);
    Optional<Client> getClient(String id);
    ResponseWithPaginationData<Client> getClients(int page, int size, Map<String, Object> filters);
}
