package com.simples.dental.professionals.application.contato.retrieve.get;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetContatoByIdUseCase extends GetContatoByIdUseCase {

    private final ContatoGateway contatoGateway;

    public DefaultGetContatoByIdUseCase(ContatoGateway contatoGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
    }

    @Override
    public ContatoOutput execute(String id) {
        final var contatoId = ContatoId.from(id);

        final var contato = contatoGateway
                .findById(contatoId)
                .orElseThrow(notFound(id));

        return ContatoOutput.from(contato);
    }

    private static Supplier<NotFoundException> notFound(String id) {
        return () -> new NotFoundException("Contato com ID %s não foi encontrado".formatted(id));
    }
}
