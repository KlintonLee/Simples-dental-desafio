package com.simples.dental.professionals.application.profissional.create;

import com.simples.dental.professionals.infrastructure.profissional.presenters.ProfissionalOutput;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

public class DefaultCreateProfissionalUseCase extends CreateProfissionalUseCase {

    private final ProfissionalGateway profissionalGateway;

    public DefaultCreateProfissionalUseCase(final ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }


    @Override
    public ProfissionalOutput execute(CreateProfissionalCommand command) {
        final var profissional = Profissional.newProfissional(command.name(), command.cargo(), command.nascimento());

        return ProfissionalOutput.with(this.profissionalGateway.create(profissional));
    }
}
