package com.simples.dental.professionals.application.profissional.update;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;

import java.time.LocalDate;

public record UpdateProfissionalCommand(
        String id,
        String nome,
        CargoProfissional cargo,
        LocalDate nascimento
) {

    public static UpdateProfissionalCommand with(
            final String id,
            final String nome,
            final CargoProfissional cargo,
            final LocalDate nascimento
    ) {
        return new UpdateProfissionalCommand(id, nome, cargo, nascimento);
    }
}
