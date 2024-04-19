package com.simples.dental.professionals.application.contato.create;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.NotFoundException;

import java.util.Objects;

public class DefaultCreateContatoUseCase extends CreateContatoUseCase {

    private final ContatoGateway contatoGateway;

    private final ProfissionalGateway profissionalGateway;

    public DefaultCreateContatoUseCase(ContatoGateway contatoGateway, ProfissionalGateway profissionalGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public CreateContatoOutput execute(CreateContatoCommand command) {
        final var profissionalId = IdProfissional.from(command.profissionalId());
        final var profissionalMissing = !this.profissionalGateway.existsById(profissionalId);
        if (profissionalMissing) {
            throw new NotFoundException("Profissional com id %s não encontrado".formatted(command.profissionalId()));
        }

        final var contato = Contato
                .newContato(command.nome(), command.contato(), command.profissionalId());
        return CreateContatoOutput.from(this.contatoGateway.create(contato));
    }
}
