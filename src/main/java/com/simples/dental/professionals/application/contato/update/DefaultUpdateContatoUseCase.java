package com.simples.dental.professionals.application.contato.update;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateContatoUseCase extends UpdateContatoUseCase {

    private final ContatoGateway contatoGateway;

    private final ProfissionalGateway profissionalGateway;

    public DefaultUpdateContatoUseCase(ContatoGateway contatoGateway, ProfissionalGateway profissionalGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public ContatoOutput execute(UpdateContatoCommand command) {
        final var contatoId = ContatoId.from(command.id());
        final var contato = contatoGateway
                .findById(contatoId)
                .orElseThrow(notFound(Contato.class, command.id()));
        final var profissionalId = IdProfissional.from(command.profissionalId());

        updateContato(command, contato, profissionalId);

        return ContatoOutput.from(contatoGateway.update(contato));
    }

    private void updateContato(UpdateContatoCommand command, Contato contato, IdProfissional profissionalId) {
        if (contato.getProfissional().getId().getValue().equals(profissionalId.getValue())) {
            contato.update(command.nome(), command.contato(), contato.getProfissional());
            return;
        }

        final var profissional = profissionalGateway.findById(profissionalId)
                .orElseThrow(notFound(Profissional.class, command.profissionalId()));
        contato.update(command.nome(), command.contato(), profissional);
    }

    private void checkProfissionalExists(IdProfissional profissionalId) {
        final var profissionalMissing = !profissionalGateway.existsById(profissionalId);
        if (profissionalMissing) {
            throw notFound(Profissional.class, profissionalId.getValue()).get();
        }
    }

    private static Supplier<NotFoundException> notFound(Class<?> clazz, String id) {
        return () -> new NotFoundException("%s com ID %s não foi encontrado".formatted(clazz.getSimpleName(), id));
    }
}
