package com.criiscz.litethinkingtechnical.app.companies;

import com.criiscz.litethinkingtechnical.app.companies.application.GetAllCompaniesUseCase;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAllCompaniesUseCaseTest {

    private CompanyRepository companyRepository;
    private GetAllCompaniesUseCase getAllCompaniesUseCase;

    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        getAllCompaniesUseCase = new GetAllCompaniesUseCase(companyRepository);
    }

    @Test
    void shouldReturnAllCompaniesAsList() {
        // Given
        List<Company> companies = List.of(
                new Company("123", "Company A", "Address A", "111111"),
                new Company("456", "Company B", "Address B", "222222")
        );

        when(companyRepository.getCompanies()).thenReturn(companies);

        // When
        List<CompanyOutput> result = getAllCompaniesUseCase.execute();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Company A", result.get(0).name());
        assertEquals("Company B", result.get(1).name());

        verify(companyRepository, times(1)).getCompanies();
    }

    @Test
    void shouldReturnEmptyListWhenNoCompaniesExist() {
        // Given
        when(companyRepository.getCompanies()).thenReturn(Collections.emptyList());

        // When
        List<CompanyOutput> result = getAllCompaniesUseCase.execute();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(companyRepository, times(1)).getCompanies();
    }

    @Test
    void shouldReturnPaginatedCompanies() {
        // Given
        int page = 1;
        int size = 5;
        Map<String, Object> filters = Map.of("sector", "technology");

        List<Company> companies = List.of(new Company("123", "Company A", "Address A", "111111"));

        Pagination pagination = Pagination.builder()
                .page(1)
                .size(5)
                .totalItems(1)
                .totalPages(1)
                .hasNext(false)
                .build();

        ResponseWithPaginationData<Company> paginatedResponse = new ResponseWithPaginationData<>(companies, pagination);

        when(companyRepository.getAllCompanies(page, size, filters)).thenReturn(paginatedResponse);

        // When
        ResponseWithPaginationData<Company> result = getAllCompaniesUseCase.execute(page, size, filters);

        // Then
        assertNotNull(result);
        assertEquals(1, result.pagination().totalItems());
        assertEquals(1, result.pagination().totalPages());
        assertFalse(result.pagination().hasNext());
        assertEquals(1, result.data().size());
        assertEquals("Company A", result.data().get(0).name());

        verify(companyRepository, times(1)).getAllCompanies(page, size, filters);
    }

    @Test
    void shouldReturnEmptyPaginationWhenNoCompaniesMatchFilters() {
        // Given
        int page = 1;
        int size = 5;
        Map<String, Object> filters = Map.of("sector", "non-existent-sector");

        Pagination pagination = Pagination.builder()
                .page(1)
                .size(5)
                .totalItems(0)
                .totalPages(0)
                .hasNext(false)
                .build();

        ResponseWithPaginationData<Company> emptyResponse = new ResponseWithPaginationData<>(Collections.emptyList(), pagination);

        when(companyRepository.getAllCompanies(page, size, filters)).thenReturn(emptyResponse);

        // When
        ResponseWithPaginationData<Company> result = getAllCompaniesUseCase.execute(page, size, filters);

        // Then
        assertNotNull(result);
        assertEquals(0, result.pagination().totalItems());
        assertEquals(0, result.pagination().totalPages());
        assertFalse(result.pagination().hasNext());
        assertTrue(result.data().isEmpty());

        verify(companyRepository, times(1)).getAllCompanies(page, size, filters);
    }
}
