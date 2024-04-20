package com.simples.dental.professionals.infrastructure.contato.models;

public record UpdateContatoInput(
        String nome,
        String contato,
        String profissional_id
) {

    public static UpdateContatoInput with(
            final String nome,
            final String contato,
            final String profissional_id
    ) {
        return new UpdateContatoInput(nome, contato, profissional_id);
    }
}
