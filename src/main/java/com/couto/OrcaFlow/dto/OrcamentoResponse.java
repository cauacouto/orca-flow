package com.couto.OrcaFlow.dto;

import com.couto.OrcaFlow.Enum.StatusOrcamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record OrcamentoResponse(
        UUID id,
        String nomeOrcamento,
        String codigoPublico,
        UUID clienteId,
      StatusOrcamento statusOrcamento,
        LocalDate validade,
        String observacao,
        BigDecimal valorTotal
) {

}
