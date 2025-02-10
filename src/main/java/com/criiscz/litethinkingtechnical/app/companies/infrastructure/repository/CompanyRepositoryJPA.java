package com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.dto.CompanyDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface CompanyRepositoryJPA extends JpaRepository<CompanyDTO, String>, JpaSpecificationExecutor<CompanyDTO> {
}
