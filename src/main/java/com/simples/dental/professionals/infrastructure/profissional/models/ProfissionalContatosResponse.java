package com.simples.dental.professionals.infrastructure.profissional.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record ProfissionalContatosResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("cargo") String cargo,
        @JsonProperty("nascimento") LocalDate nascimento,
        @JsonProperty("contatos") List<ContatoForProfissonalResponse> contatos,
        @JsonProperty("active") boolean active,
        @JsonProperty("created_date") LocalDate createdDate
) {
}

