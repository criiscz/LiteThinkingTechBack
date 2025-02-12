package com.criiscz.litethinkingtechnical.app.companies;

import com.criiscz.litethinkingtechnical.app.companies.application.DeleteCompanyUseCase;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCompanyUseCaseTest {

    private CompanyRepository companyRepository;
    private DeleteCompanyUseCase deleteCompanyUseCase;

    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        deleteCompanyUseCase = new DeleteCompanyUseCase(companyRepository);
    }

    @Test
    void shouldDeleteCompanySuccessfully() {
        // Given
        String nit = "123456789";
        Company company = new Company(nit, "Test Company", "123 Street", "1234567890");

        when(companyRepository.getCompanyById(nit)).thenReturn(Optional.of(company));
        when(companyRepository.deleteCompany(nit)).thenReturn(true);

        // When
        boolean result = deleteCompanyUseCase.execute(nit);

        // Then
        assertTrue(result);
        verify(companyRepository, times(1)).getCompanyById(nit);
        verify(companyRepository, times(1)).deleteCompany(nit);
    }

    @Test
    void shouldThrowExceptionWhenCompanyNotFound() {
        // Given
        String nit = "999999999";
        when(companyRepository.getCompanyById(nit)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ItemNotFoundException.class, () -> deleteCompanyUseCase.execute(nit));

        verify(companyRepository, times(1)).getCompanyById(nit);
        verify(companyRepository, never()).deleteCompany(anyString());
    }
}
