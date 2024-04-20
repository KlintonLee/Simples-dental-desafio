package com.simples.dental.professionals.infrastructure.profissional.presenters;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.infrastructure.profissional.models.ProfissionalResponse;

public interface ProfissionalPresenter {

    static ProfissionalResponse present(ProfissionalOutput profissionalOutput) {
        return new ProfissionalResponse(
                profissionalOutput.id(),
                profissionalOutput.nome(),
                profissionalOutput.cargo().toString().toLowerCase(),
                profissionalOutput.nascimento(),
                profissionalOutput.active(),
                profissionalOutput.createdDate()
        );
    }
}
