package com.simples.dental.professionals.application.profissional.create;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;

import java.time.Instant;

public record CreateProfissionalCommand(
        String name,
        CargoProfissional cargo,
        Instant nascimento
) {

    public static CreateProfissionalCommand with(
            final String name,
            final CargoProfissional cargo,
            final Instant nascimento
    ) {
        return new CreateProfissionalCommand(name, cargo, nascimento);
    }
}
