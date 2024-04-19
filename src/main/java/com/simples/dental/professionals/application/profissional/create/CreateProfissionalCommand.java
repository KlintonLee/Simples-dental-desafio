package com.simples.dental.professionals.application.profissional.create;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;

import java.time.LocalDate;

public record CreateProfissionalCommand(
        String name,
        CargoProfissional cargo,
        LocalDate nascimento
) {

    public static CreateProfissionalCommand with(
            final String name,
            final CargoProfissional cargo,
            final LocalDate nascimento
    ) {
        return new CreateProfissionalCommand(name, cargo, nascimento);
    }
}
