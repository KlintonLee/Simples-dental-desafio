package com.simples.dental.professionals.infrastructure.profissional.presenters;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;

import java.time.LocalDate;

public record ProfissionalOutput(
        String id,
        String nome,
        CargoProfissional cargo,
        LocalDate nascimento,
        boolean active,
        LocalDate createdDate
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
