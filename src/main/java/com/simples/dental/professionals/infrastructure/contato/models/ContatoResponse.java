package com.simples.dental.professionals.infrastructure.contato.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simples.dental.professionals.application.profissional.ProfissionalOutput;

public record ContatoResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("contato") String contato,
        @JsonProperty("profissional") ProfissionalOutput profissional
) {
}
