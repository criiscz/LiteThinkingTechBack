package com.criiscz.litethinkingtechnical.app.clients;

import com.criiscz.litethinkingtechnical.app.clients.application.CreateClientUseCase;
import com.criiscz.litethinkingtechnical.app.clients.domain.entity.Client;
import com.criiscz.litethinkingtechnical.app.clients.domain.repository.ClientRepository;
import com.criiscz.litethinkingtechnical.app.clients.ports.in.ClientInput;
import com.criiscz.litethinkingtechnical.app.clients.ports.out.ClientOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CreateClientUseCase createClientUseCase;

    @Test
    void execute_ShouldCreateAndReturnClient_WhenInputIsValid() {
        // Arrange
        ClientInput clientInput = new ClientInput("1234", "John Doe",  "1234567890");
        Client mockClient = new Client( "1234", "John Doe",  "1234567890");

        when(clientRepository.saveClient(any(Client.class))).thenReturn(mockClient);

        // Act
        ClientOutput result = createClientUseCase.execute(clientInput);

        // Assert
        assertNotNull(result);
        assertEquals("1234", result.id());
        assertEquals("John Doe", result.name());
        assertEquals("1234567890", result.phone());

        verify(clientRepository, times(1)).saveClient(any(Client.class));
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        ClientInput clientInput = new ClientInput("1234", "John Doe",  "1234567890");

        when(clientRepository.saveClient(any(Client.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> createClientUseCase.execute(clientInput));
        assertEquals("Database error", exception.getMessage());

        verify(clientRepository, times(1)).saveClient(any(Client.class));
    }
}
