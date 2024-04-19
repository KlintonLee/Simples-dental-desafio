package com.simples.dental.professionals.application.profissional;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;

import java.time.Instant;

public record ProfissionalOutput(
        String id,
        String nome,
        CargoProfissional cargo,
        Instant nascimento,
        boolean active,
        Instant createdDate
) {

    public static ProfissionalOutput with(final Profissional profissional) {
        return new ProfissionalOutput(
                profissional.getId().getValue(),
                profissional.getNome(),
                profissional.getCargo(),
                profissional.getNascimento(),
                profissional.isActive(),
                profissional.getCreatedDate()
        );
    }
}
