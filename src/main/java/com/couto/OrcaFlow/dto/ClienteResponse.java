package com.couto.OrcaFlow.dto;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        UUID usuarioId,
        String nome,
        Integer telefone
) {
}
