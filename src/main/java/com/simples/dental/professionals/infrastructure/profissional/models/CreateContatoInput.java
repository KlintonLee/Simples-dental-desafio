package com.simples.dental.professionals.infrastructure.profissional.models;

public record CreateContatoInput(
        String nome,
        String contato,
        String profissional_id
) {
    public static CreateContatoInput with(
            final String nome,
            final String contato,
            final String profissional_id
    ) {
      return new CreateContatoInput(nome, contato, profissional_id);
    }
}
