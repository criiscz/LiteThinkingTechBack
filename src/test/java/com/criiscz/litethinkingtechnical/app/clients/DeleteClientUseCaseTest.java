package com.criiscz.litethinkingtechnical.app.clients;

import com.criiscz.litethinkingtechnical.app.clients.application.DeleteClientUseCase;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteClientUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private DeleteClientUseCase deleteClientUseCase;

    @Test
    void execute_ShouldDeleteClient_WhenClientExists() {
        // Arrange
        String clientId = "123";
        Client mockClient = new Client(clientId, "John Doe", "1234567890");

        when(clientRepository.getClient(clientId)).thenReturn(Optional.of(mockClient));
        when(clientRepository.deleteClient(clientId)).thenReturn(true);

        // Act
        boolean result = deleteClientUseCase.execute(clientId);

        // Assert
        assertTrue(result);
        verify(clientRepository, times(1)).getClient(clientId);
        verify(clientRepository, times(1)).deleteClient(clientId);
    }

    @Test
    void execute_ShouldThrowException_WhenClientNotFound() {
        // Arrange
        String clientId = "123";

        when(clientRepository.getClient(clientId)).thenReturn(Optional.empty());

        // Act & Assert
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> deleteClientUseCase.execute(clientId));
        assertEquals("Client not found", exception.getMessage());

        verify(clientRepository, times(1)).getClient(clientId);
        verify(clientRepository, never()).deleteClient(anyString());
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFailsToDelete() {
        // Arrange
        String clientId = "123";
        Client mockClient = new Client(clientId, "John Doe", "1234567890");

        when(clientRepository.getClient(clientId)).thenReturn(Optional.of(mockClient));
        when(clientRepository.deleteClient(clientId)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> deleteClientUseCase.execute(clientId));
        assertEquals("Database error", exception.getMessage());

        verify(clientRepository, times(1)).getClient(clientId);
        verify(clientRepository, times(1)).deleteClient(clientId);
    }
}
