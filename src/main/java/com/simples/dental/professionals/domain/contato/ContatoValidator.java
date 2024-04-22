package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.validations.Validator;
import com.simples.dental.professionals.exceptions.UnprocessableEntityException;

import java.util.Objects;

public class ContatoValidator implements Validator {

    private final Contato contato;

    public ContatoValidator(final Contato contato) {
        this.contato = Objects.requireNonNull(contato);
    }

    public void validate() {
        final var nome = contato.getNome();

        if (nome == null || nome.isBlank()) {
            throw new UnprocessableEntityException("O 'nome' do contato não pode ser nulo ou vazio");
        }

        int MAX_NAME_LENGTH = 255;
        if (nome.length() > MAX_NAME_LENGTH) {
            throw new UnprocessableEntityException("O 'nome' não deve ser maior do que 255");
        }
    }
}
