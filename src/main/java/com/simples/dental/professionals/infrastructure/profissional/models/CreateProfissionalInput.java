package com.simples.dental.professionals.infrastructure.profissional.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateProfissionalInput(
        String nome,
        String cargo,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate nascimento
) {
}
