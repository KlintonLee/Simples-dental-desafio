package com.simples.dental.professionals.infrastructure.profissional.models;

public record CreateProfissionalInput(
        String nome,
        String cargo,
        String nascimento
) {
}
