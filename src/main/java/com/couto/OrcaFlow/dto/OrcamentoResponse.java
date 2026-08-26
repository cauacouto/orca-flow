package com.couto.OrcaFlow.dto;

import com.couto.OrcaFlow.Enum.StatusOrcamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record OrcamentoResponse(
        UUID id,
        String codigo,
        UUID clienteId,
        StatusOrcamento status,
        LocalDate validade,
        String observacao,
        BigDecimal valorTotal
) {
}
