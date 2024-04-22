package com.simples.dental.professionals.application.contato.update;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

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
}
