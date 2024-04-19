package com.simples.dental.professionals.application.contato.delete;

import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;

import java.util.Objects;

public class DefaultDeleteContatoUseCase extends DeleteContatoUseCase {

    private final ContatoGateway contatoGateway;

    public DefaultDeleteContatoUseCase(ContatoGateway contatoGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
    }

    @Override
    public void execute(String id) {
        final var contatoId = ContatoId.from(id);
        if (contatoGateway.existsById(contatoId)) {
            contatoGateway.deleteById(contatoId);
        }
    }
}
