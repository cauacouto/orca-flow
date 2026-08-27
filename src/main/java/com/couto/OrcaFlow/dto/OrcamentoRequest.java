package com.couto.OrcaFlow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record OrcamentoRequest(UUID clientId,
                               LocalDate validade,
                               String observacao

                               ) {
}
