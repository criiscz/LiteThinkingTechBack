package com.criiscz.litethinkingtechnical.app.clients;

import com.criiscz.litethinkingtechnical.app.clients.application.GetClientByIdUseCase;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetClientByIdUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private GetClientByIdUseCase getClientByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_whenClientExists_shouldReturnClientOutput() {
        // Arrange
        String clientId = "12345";
        Client client = Client.builder().id(clientId).name("John Doe").phone("123456789").build();
        when(clientRepository.getClient(clientId)).thenReturn(Optional.of(client));

        // Act
        ClientOutput result = getClientByIdUseCase.execute(clientId);

        // Assert
        assertNotNull(result);
        assertEquals(client.id(), result.id());
        assertEquals(client.name(), result.name());
        assertEquals(client.phone(), result.phone());
        verify(clientRepository, times(1)).getClient(clientId);
    }

    @Test
    void execute_whenClientDoesNotExist_shouldThrowItemNotFoundException() {
        // Arrange
        String clientId = "12345";
        when(clientRepository.getClient(clientId)).thenReturn(Optional.empty());

        // Act & Assert
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> getClientByIdUseCase.execute(clientId));
        assertEquals("Client not found", exception.getMessage());
        verify(clientRepository, times(1)).getClient(clientId);
    }
}
