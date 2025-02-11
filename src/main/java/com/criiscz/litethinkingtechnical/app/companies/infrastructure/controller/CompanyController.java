package com.criiscz.litethinkingtechnical.app.companies.infrastructure.controller;

import com.criiscz.litethinkingtechnical.app.companies.application.*;
import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.ports.in.CompanyInput;
import com.criiscz.litethinkingtechnical.app.companies.ports.out.CompanyOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.utils.AppConstants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/company")
@AllArgsConstructor
@Tag(name = "Company", description = "The Company API")
public class CompanyController {

    private final GetAllCompaniesUseCase getAllCompaniesUseCase;
    private final CreateCompanyUseCase createCompanyUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;


    @GetMapping("/")
    public ResponseEntity<ResponseWithPaginationData<Company>> getAllCompanies(
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        Map<String, Object> filters = buildFilters(name, phone, address);
        return ResponseEntity.ok(getAllCompaniesUseCase.execute(page, size, filters));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyOutput>> getAllCompanies() {
        return ResponseEntity.ok(getAllCompaniesUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyOutput> getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(getCompanyByIdUseCase.execute(id));
    }

    @PostMapping("/")
    public ResponseEntity<CompanyOutput> createCompany(@RequestBody CompanyInput company) {
        return ResponseEntity.ok(createCompanyUseCase.execute(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable String id) {
        return ResponseEntity.ok(deleteCompanyUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyOutput> updateCompany(
            @PathVariable(value = "id") String id,
            @RequestBody CompanyInput company
    ) {
        return ResponseEntity.ok(updateCompanyUseCase.execute(company, id));
    }

    private Map<String, Object> buildFilters(String name, String phone, String address) {
        Map<String, Object> filters = new HashMap<>();
        if (name != null) filters.put("name", name);
        if (phone != null) filters.put("phone", phone);
        if (address != null) filters.put("address", address);

        return filters;
    }


}
