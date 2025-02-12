package com.criiscz.litethinkingtechnical.app.clients;

import com.criiscz.litethinkingtechnical.app.clients.application.GetAllClientsUseCase;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllClientsUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private GetAllClientsUseCase getAllClientsUseCase;

    @Test
    void execute_ShouldReturnListOfClients_WhenClientsExist() {
        // Arrange
        Client client1 = new Client("1", "John Doe", "john.doe@example.com");
        Client client2 = new Client("2", "Jane Doe", "jane.doe@example.com");

        when(clientRepository.getClients()).thenReturn(List.of(client1, client2));

        // Act
        List<ClientOutput> result = getAllClientsUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Doe", result.get(1).name());

        verify(clientRepository, times(1)).getClients();
    }

    @Test
    void execute_ShouldReturnPaginatedResponse_WhenPaginationIsApplied() {
        // Arrange
        Client client1 = new Client("1", "John Doe", "john.doe@example.com");
        Client client2 = new Client("2", "Jane Doe", "jane.doe@example.com");
        ResponseWithPaginationData<Client> paginatedResponse = new ResponseWithPaginationData<>(List.of(client1, client2), new Pagination(0, 2, 2, 1, false));

        when(clientRepository.getClients(0, 2, Map.of())).thenReturn(paginatedResponse);

        // Act
        ResponseWithPaginationData<Client> result = getAllClientsUseCase.execute(0, 2, Map.of());

        // Assert
        assertNotNull(result);
        assertEquals(2, result.data().size());
        assertEquals(2, result.pagination().totalItems());

        verify(clientRepository, times(1)).getClients(0, 2, Map.of());
    }

    @Test
    void execute_ShouldReturnEmptyList_WhenNoClientsExist() {
        // Arrange
        when(clientRepository.getClients()).thenReturn(List.of());

        // Act
        List<ClientOutput> result = getAllClientsUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(clientRepository, times(1)).getClients();
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        when(clientRepository.getClients()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> getAllClientsUseCase.execute());
        assertEquals("Database error", exception.getMessage());

        verify(clientRepository, times(1)).getClients();
    }
}
