package com.simples.dental.professionals.infrastructure.profissional.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateOrUpdateProfessionalInput(
        String nome,
        String cargo,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate nascimento
) {
}
