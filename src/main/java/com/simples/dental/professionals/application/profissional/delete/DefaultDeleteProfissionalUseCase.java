package com.simples.dental.professionals.application.profissional.delete;

import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

public class DefaultDeleteProfissionalUseCase extends DeleteProfissionalUseCase {

    private final ProfissionalGateway profissionalGateway;

    public DefaultDeleteProfissionalUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public void execute(String id) {
        final var profissionalId = IdProfissional.from(id);

        final var optionalProfissional = this.profissionalGateway.findById(profissionalId);
        if (optionalProfissional.isPresent()) {
            final var profissional = optionalProfissional.get();
            this.profissionalGateway.update(profissional.deactivate());
        }
    }
}
