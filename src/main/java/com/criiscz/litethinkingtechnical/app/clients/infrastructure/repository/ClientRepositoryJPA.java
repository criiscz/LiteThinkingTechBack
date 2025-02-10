package com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.dto.ClientDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface ClientRepositoryJPA extends JpaRepository<ClientDTO, String>, JpaSpecificationExecutor<ClientDTO> {
}
