package com.simples.dental.professionals.application.profissional.retrieve.get;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;

import java.time.LocalDate;
import java.util.List;

public record ProfissionalWithContactsOutput(
        String id,
        String nome,
        CargoProfissional cargo,
        LocalDate nascimento,
        List<Contato> contatos,
        boolean active,
        LocalDate createdDate
) {
    public static ProfissionalWithContactsOutput with(final Profissional profissional, final List<Contato> contatos) {
        return new ProfissionalWithContactsOutput(
                profissional.getId().getValue(),
                profissional.getNome(),
                profissional.getCargo(),
                profissional.getNascimento(),
                contatos,
                profissional.isActive(),
                profissional.getCreatedDate()
        );
    }
}
