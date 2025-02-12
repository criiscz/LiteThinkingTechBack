package com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.companies.domain.entity.Company;
import com.criiscz.litethinkingtechnical.app.companies.domain.repository.CompanyRepository;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.dto.CompanyDTO;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.mapper.CompanyMapper;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyRepositoryJPA companyRepositoryJPA;


    @Override
    public Company saveCompany(Company CompanyInput) {
        return CompanyMapper.toDomain(companyRepositoryJPA.save(CompanyMapper.toDTO(CompanyInput)));
    }

    @Override
    public boolean deleteCompany(String id) {
        return companyRepositoryJPA.findById(id).map(companyDTO -> {
            companyRepositoryJPA.delete(companyDTO);
            return true;
        }).orElseThrow(() -> new ItemNotFoundException("Company not found"));
    }

    @Override
    public Optional<Company> getCompanyById(String id) {
        return companyRepositoryJPA.findById(id).map(CompanyMapper::toDomain);
    }

    @Override
    public ResponseWithPaginationData<Company> getAllCompanies(int page, int size, Map<String, Object> filters) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<CompanyDTO> specification = buildSpecification(filters);
        var companies = companyRepositoryJPA.findAll(specification, pageable);
        return new ResponseWithPaginationData<>(
                companies.getContent().stream().map(CompanyMapper::toDomain).toList(),
                Pagination.builder()
                        .page(page)
                        .size(size)
                        .totalItems(companies.getTotalElements())
                        .totalPages(companies.getTotalPages())
                        .hasNext(companies.hasNext())
                        .build()
        );
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepositoryJPA.findAll().stream().map(CompanyMapper::toDomain).toList();
    }

    @Override
    public Company updateCompany(Company company, String id) {
        CompanyDTO companyDTO = companyRepositoryJPA.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Company not found")
        );
        companyDTO.setName(company.name());
        companyDTO.setPhone(company.phone());
        companyDTO.setAddress(company.address());
        return CompanyMapper.toDomain(companyRepositoryJPA.save(companyDTO));
    }

    private Specification<CompanyDTO> buildSpecification(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((key, value) -> {
                Predicate predicate = switch (key) {
                    case "name" -> criteriaBuilder.like(root.get("name"), "%" + value + "%");
                    case "phone" -> criteriaBuilder.like(root.get("phone"), "%" + value + "%");
                    case "address" -> criteriaBuilder.like(root.get("address"), "%" + value + "%");
                    default -> null;
                };
                if (predicate != null) {
                    predicates.add(predicate);
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
