package com.criiscz.litethinkingtechnical.app.companies;

import com.criiscz.litethinkingtechnical.app.companies.application.CreateCompanyUseCase;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.in.CompanyInput;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCompanyUseCaseTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CreateCompanyUseCase createCompanyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldCreateCompanySuccessfully() {
        // Arrange
        CompanyInput input = new CompanyInput("12345", "Test Company", "1234567890", "Test Address");
        Company company = CompanyInput.to(input);
        CompanyOutput expectedOutput = CompanyOutput.fromCompany(company);

        when(companyRepository.saveCompany(any(Company.class))).thenReturn(company);

        // Act
        CompanyOutput result = createCompanyUseCase.execute(input);

        // Assert
        assertNotNull(result);
        assertEquals(expectedOutput.NIT(), result.NIT());
        assertEquals(expectedOutput.name(), result.name());
        assertEquals(expectedOutput.phone(), result.phone());
        assertEquals(expectedOutput.address(), result.address());

        verify(companyRepository, times(1)).saveCompany(any(Company.class));
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        CompanyInput input = new CompanyInput("12345", "Test Company", "1234567890", "Test Address");
        when(companyRepository.saveCompany(any(Company.class))).thenThrow(new RuntimeException("Database Error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> createCompanyUseCase.execute(input));
        assertEquals("Database Error", exception.getMessage());

        verify(companyRepository, times(1)).saveCompany(any(Company.class));
    }
}
