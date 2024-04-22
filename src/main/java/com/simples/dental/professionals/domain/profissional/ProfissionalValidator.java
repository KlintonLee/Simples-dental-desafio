package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.domain.exceptions.UnprocessableEntityException;

import java.util.Objects;

public class ProfissionalValidator {

    private final Profissional profissional;

    public ProfissionalValidator(final Profissional profissional) {
        this.profissional = Objects.requireNonNull(profissional);
    }

    public void validate() {
        final var nome = profissional.getNome();
        if (nome == null || nome.isBlank()) {
            throw new UnprocessableEntityException("O 'nome' do contato não pode ser nulo ou vazio");
        }

        if (nome.length() > 255) {
            throw new UnprocessableEntityException("O 'nome' não deve ser maior do que 255");
        }
    }
}
