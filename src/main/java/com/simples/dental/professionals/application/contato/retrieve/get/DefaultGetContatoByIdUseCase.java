package com.simples.dental.professionals.application.contato.retrieve.get;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

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
                .orElseThrow(notFound(Contato.class, id));

        return ContatoOutput.from(contato);
    }
}
