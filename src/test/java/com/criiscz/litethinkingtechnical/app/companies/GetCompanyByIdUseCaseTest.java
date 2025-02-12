package com.criiscz.litethinkingtechnical.app.companies;

import com.criiscz.litethinkingtechnical.app.companies.application.GetCompanyByIdUseCase;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCompanyByIdUseCaseTest {

    private CompanyRepository companyRepository;
    private GetCompanyByIdUseCase getCompanyByIdUseCase;

    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        getCompanyByIdUseCase = new GetCompanyByIdUseCase(companyRepository);
    }

    @Test
    void shouldReturnCompanyWhenIdExists() {
        // Given
        String companyId = "123";
        Company company = new Company(companyId, "Company A", "Address A", "111111");

        when(companyRepository.getCompanyById(companyId)).thenReturn(Optional.of(company));

        // When
        CompanyOutput result = getCompanyByIdUseCase.execute(companyId);

        // Then
        assertNotNull(result);
        assertEquals(companyId, result.NIT());
        assertEquals("Company A", result.name());

        verify(companyRepository, times(1)).getCompanyById(companyId);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        // Given
        String nonExistentId = "999";

        when(companyRepository.getCompanyById(nonExistentId)).thenReturn(Optional.empty());

        // When / Then
        ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class,
                () -> getCompanyByIdUseCase.execute(nonExistentId));

        assertEquals("Client not found", thrown.getMessage());

        verify(companyRepository, times(1)).getCompanyById(nonExistentId);
    }
}
