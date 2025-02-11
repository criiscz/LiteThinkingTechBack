package com.criiscz.litethinkingtechnical.app.clients.infrastructure.controller;

import com.criiscz.litethinkingtechnical.app.clients.application.*;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.ports.in.ClientInput;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.utils.AppConstants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Clients", description = "Clients API")
public class ClientController {

    private final GetAllClientsUseCase getAllClientsUseCase;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    @GetMapping("/")
    public ResponseEntity<ResponseWithPaginationData<Client>> getAllClients(
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        Map<String, Object> filters = buildFilters(name, phone);
        return ResponseEntity.ok(getAllClientsUseCase.execute(page, size, filters));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientOutput>> getAllClients() {
        return ResponseEntity.ok(getAllClientsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOutput> getClientById(@PathVariable String id) {
        return ResponseEntity.ok(getClientByIdUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable String id) {
        return ResponseEntity.ok(deleteClientUseCase.execute(id));
    }

    @PostMapping("/")
    public ResponseEntity<ClientOutput> createClient(@RequestBody ClientInput clientInput) {
        return ResponseEntity.ok(createClientUseCase.execute(clientInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientOutput> updateClient(
            @PathVariable(value = "id") String id,
            @RequestBody ClientInput clientInput
    ) {
        return ResponseEntity.ok(updateClientUseCase.execute(clientInput, id));
    }

    private Map<String, Object> buildFilters(String name, String phone) {
        Map<String, Object> filters = new HashMap<>();
        if (name != null) filters.put("name", name);
        if (phone != null) filters.put("phone", phone);

        return filters;
    }


}
