package com.simples.dental.professionals.infrastructure.profissional.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ProfissionalResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("cargo") String cargo,
        @JsonProperty("nascimento") LocalDate nascimento,
        @JsonProperty("active") boolean active,
        @JsonProperty("created_date") LocalDate createdDate
) {
}
