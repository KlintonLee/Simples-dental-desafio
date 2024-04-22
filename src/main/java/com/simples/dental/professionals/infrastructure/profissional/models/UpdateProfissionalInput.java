package com.simples.dental.professionals.infrastructure.profissional.models;

public record UpdateProfissionalInput(
        String nome,
        String cargo,
        String nascimento
) {
}
