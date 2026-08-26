package com.couto.OrcaFlow.dto;

import java.math.BigDecimal;

public record ItemRequest(
        String nome,
        String descricao,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
