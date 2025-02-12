package com.criiscz.litethinkingtechnical.app.companies;

import com.criiscz.litethinkingtechnical.app.companies.application.UpdateCompanyUseCase;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.in.CompanyInput;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCompanyUseCaseTest {

    private CompanyRepository companyRepository;
    private UpdateCompanyUseCase updateCompanyUseCase;

    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        updateCompanyUseCase = new UpdateCompanyUseCase(companyRepository);
    }

    @Test
    void shouldUpdateCompanyWhenExists() {
        // Given
        String companyId = "123";
        CompanyInput input = new CompanyInput("","New Name", "New Address", "222222");
        Company existingCompany = new Company(companyId, "Old Name", "Old Address", "111111");
        Company updatedCompany = new Company(companyId, "New Name", "New Address", "222222");

        when(companyRepository.getCompanyById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.updateCompany(any(Company.class), eq(companyId))).thenReturn(updatedCompany);

        // When
        CompanyOutput result = updateCompanyUseCase.execute(input, companyId);

        // Then
        assertNotNull(result);
        assertEquals("New Name", result.name());
        assertEquals("New Address", result.address());

        verify(companyRepository, times(1)).updateCompany(any(Company.class), eq(companyId));
    }
}
