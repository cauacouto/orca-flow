package com.couto.OrcaFlow.dto;

import com.couto.OrcaFlow.Enum.Roles;

import java.util.UUID;

public record UsuarioDto(
        UUID id,
        String email,
        String name,
        String picture,
        Roles role,
        boolean onboardingCompleted
) {
}
