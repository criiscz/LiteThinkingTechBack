package com.criiscz.litethinkingtechnical.app.clients;

import com.criiscz.litethinkingtechnical.app.clients.application.UpdateClientUseCase;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.in.ClientInput;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateClientUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private UpdateClientUseCase updateClientUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_whenClientExists_shouldUpdateAndReturnClientOutput() {
        // Arrange
        String clientId = "12345";
        ClientInput clientInput = new ClientInput(clientId, "Updated Name", "987654321");
        Client updatedClient = Client.builder()
                .id(clientId)
                .name(clientInput.name())
                .phone(clientInput.phone())
                .build();

        when(clientRepository.updateClient(any(Client.class), eq(clientId))).thenReturn(updatedClient);

        // Act
        ClientOutput result = updateClientUseCase.execute(clientInput, clientId);

        // Assert
        assertNotNull(result);
        assertEquals(clientId, result.id());
        assertEquals(clientInput.name(), result.name());
        assertEquals(clientInput.phone(), result.phone());
        verify(clientRepository, times(1)).updateClient(any(Client.class), eq(clientId));
    }
}
