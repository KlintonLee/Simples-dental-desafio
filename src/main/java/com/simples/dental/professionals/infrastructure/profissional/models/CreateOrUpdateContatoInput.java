package com.simples.dental.professionals.infrastructure.profissional.models;

public record CreateOrUpdateContatoInput(
        String nome,
        String contato,
        String profissional_id
) {
    public static CreateOrUpdateContatoInput with(
            final String nome,
            final String contato,
            final String profissional_id
    ) {
      return new CreateOrUpdateContatoInput(nome, contato, profissional_id);
    }
}
