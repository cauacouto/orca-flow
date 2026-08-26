package com.couto.OrcaFlow.repository.OrcamentoRepository;

import com.couto.OrcaFlow.domin.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrcamentoRepository extends JpaRepository<Orcamento, UUID> {
}
