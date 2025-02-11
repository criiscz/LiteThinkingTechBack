package com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.dto.ClientDTO;
import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.mapper.ClientMapper;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import jakarta.persistence.criteria.Predicate;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientRepositoryJPA clientRepositoryJPA;


    @Override
    public Client saveClient(Client clientInput) {
        return ClientMapper.toDomain(clientRepositoryJPA.save(ClientMapper.toDTO(clientInput)));
    }

    @Override
    public Client updateClient(Client clientInput, String id) {
        return clientRepositoryJPA.findById(id).map(clientDTO -> {
            clientDTO.setName(clientInput.name());
            clientDTO.setPhone(clientInput.phone());
            return ClientMapper.toDomain(clientRepositoryJPA.save(clientDTO));
        }).orElse(null);
    }

    @Override
    public boolean deleteClient(String id) {
        return clientRepositoryJPA.findById(id).map(clientDTO -> {
            clientRepositoryJPA.delete(clientDTO);
            return true;
        }).orElseThrow(() -> new ItemNotFoundException("Client not found"));
    }

    @Override
    public Optional<Client> getClient(String id) {
        return clientRepositoryJPA.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public ResponseWithPaginationData<Client> getClients(int page, int size, Map<String, Object> filters) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<ClientDTO> specification = buildSpecification(filters);
        var clients = clientRepositoryJPA.findAll(specification, pageable);
        return new ResponseWithPaginationData<>(
                clients.getContent().stream().map(ClientMapper::toDomain).toList(),
                Pagination.builder()
                        .page(page)
                        .size(size)
                        .totalItems(clients.getTotalElements())
                        .totalPages(clients.getTotalPages())
                        .hasNext(clients.hasNext())
                        .build()
        );
    }

    @Override
    public List<Client> getClients() {
        return clientRepositoryJPA.findAll().stream().map(ClientMapper::toDomain).toList();
    }

    private Specification<ClientDTO> buildSpecification(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((key, value) -> {
                Predicate predicate = switch (key) {
                    case "name" -> criteriaBuilder.like(root.get("name"), "%" + value + "%");
                    case "phone" -> criteriaBuilder.like(root.get("phone"), "%" + value + "%");
                    default -> null;
                };
                if (predicate != null) {
                    predicates.add(predicate);
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
